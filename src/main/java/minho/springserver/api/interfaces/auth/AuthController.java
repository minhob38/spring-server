package minho.springserver.api.interfaces.auth;

import minho.springserver.api.application.auth.AuthApplication;
import minho.springserver.api.domain.auth.AuthCommand;
import minho.springserver.api.domain.auth.AuthInfo;
import minho.springserver.api.domain.auth.SessionUser;
import minho.springserver.dto.*;
import minho.springserver.exception.AuthException;
import minho.springserver.response.SuccessResponse;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import javax.transaction.Transactional;

/*
[Request]
* query / form 요청
- request.getParameter - (HttpServletRequest instance)
- @RequestParam("key")
- @RequestParam Map<String, Object>
- @ModelAttribute
* path parameter 요청
- @PathVariable

* body 요청
- InputStream + ObjectMapper
- @RequestBody
 */

/*
[Response]
* 문자열 응답
- response.getWriter().write("res") - (HttpServletResponse instance)
- @ResponseBody + return 문자열
- ResponseEntity<>("res", HttpStatus.OK) - (ResponseEntity<String> class)

* json 응답
- ResponseEntity<>("res", HttpStatus.OK) - (ResponseEntity<해당 Data Class> class)
- @ResponseBody + return <해당 Data Class>
 */

/*
[Controller]
- @RestController는 @Controller + @ResponseBody와 같습니다.
- @RestController -> @Controller에 @Component가 있습니다.
- @RestController는 @ResponseBody를 가지고 있어, view template을 찾지 않습니다.
*/
@Transactional
@RestController
public class AuthController {
    private final Logger log = LoggerFactory.getLogger((getClass()));
    private final AuthApplication authApplication;

    @Autowired
    // 아래 생성자는 lombok의 @RequiredArgsConstructor로 생략가능합니다.
    public AuthController(AuthApplication authApplication) {
        this.authApplication = authApplication;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({AuthException.class}) // <- 별도 설정없이, @ExceptionHandler를 붙이면 됩니다.
    public ErrorResponse authExceptionHandler(AuthException e) {
        // 같은 exception에 대해 exception handler와 advice가 있으면, exception handler가 실행됩니다.
        System.out.println("auth exception handler");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        return errorResponse;
    }

    /* api
    https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-requestmapping
    https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-methods
    */
    @RequestMapping(value = "/api/auth/signup", method = RequestMethod.POST)
    public ResponseEntity<?> postSignUp(HttpServletRequest request, HttpServletResponse response) throws AuthException {
        log.info("info api log={}", "/api/auth/signup");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // command 생성
        AuthCommand.SignupCommand command = new AuthCommand.SignupCommand(email, password);

        // interface -> application
        AuthInfo.SignupInfo signupInfo = this.authApplication.signup(command);

        // 응답 생성
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("user signed up");
        successResponse.setData("token");
        return new ResponseEntity<>(successResponse, HttpStatus.CREATED);
    }

    @PostMapping(value = "/api/auth/signin")
    public ResponseEntity<?> postSignIn(HttpServletRequest request, @RequestParam("email") String email, @RequestParam("password") String password) throws AuthException {
        // command 생성
        AuthCommand.SigninCommand command = new AuthCommand.SigninCommand(email, password);

        // interface -> application
        AuthInfo.SigninInfo signinInfo = this.authApplication.signin(command);

        // session 설정
        SessionUser sessionUser = new SessionUser(signinInfo.getUserId());
        HttpSession session = request.getSession(true); // session을 찾아서 없으면, 새로 session을 만듬
        session.setAttribute("auth-key", sessionUser); // session에 담을 정보(sessionUser)를 넣어줌

        // 응답 생성
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("user signed in");
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    /* BidingResult를 인자로 넘겨주면, controller가 실행됩니다. (@Validated가 있을때 BindingResult를 인자로 넘겨주지 않으면 controller에서 error를 처리할수 없습니다.) */
    @PatchMapping(value = "/api/auth/password")
    public SuccessResponse patchPassword(HttpServletRequest request, @Validated @ModelAttribute PatchPasswordForm patchPasswordForm, BindingResult bindingResult) throws AuthException {

        if (bindingResult.hasErrors()) {
            System.out.println((bindingResult.getAllErrors()));
        }

        // session parsing
        HttpSession session = request.getSession(false);
        SessionUser sessionUser = (SessionUser) session.getAttribute("auth-key");
        Long userId = sessionUser.getUserId();

        String currentPassword =  patchPasswordForm.getCurrentPassword();
        String newPassword = patchPasswordForm.getNewPassword();

        // command 생성
        AuthCommand.UpdatePasswordCommand command = new AuthCommand.UpdatePasswordCommand(userId, newPassword, currentPassword);

        // interface -> application
        this.authApplication.updatePassword(command);

        // 응답 생성
        SuccessResponse successResponse = new SuccessResponse();
        return successResponse;
    }

    @GetMapping(value = "/api/auth/me")
    public ResponseEntity<?> getMe(@SessionAttribute(name = "auth-key", required = false) SessionUser user) throws AuthException {
        Long userId = user.getUserId();

        // command 생성
        AuthCommand.ReadMeCommand command = new AuthCommand.ReadMeCommand(userId);

        // interface -> application
        AuthInfo.UserInfo userInfo = this.authApplication.findMe(command);

        // 응답 생성
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("my information");
        successResponse.setData(userInfo);
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/api/auth/logout")
    public String postLogout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        // session 지우기
        if (session != null) session.invalidate();

        // cookie 지우기 (path도 설정)
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);

        // 응답 생성
        return "user logged out" ;
    }

    @DeleteMapping(value = "/api/auth/signout")
    public String deleteSignOut(@SessionAttribute(name = "auth-key", required = false) SessionUser user) throws AuthException {
        Long userId = user.getUserId();

        // command 생성
        AuthCommand.SignoutCommand command = new AuthCommand.SignoutCommand(userId);

        // interface -> application
        this.authApplication.signout(command);

        // 응답 생성
        return "user signed out" ;
    }
}
