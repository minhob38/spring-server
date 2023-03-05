package minho.springserver.controller;

import minho.springserver.application.auth.AuthApplication;
import minho.springserver.domain.auth.AuthCommand;
import minho.springserver.domain.auth.AuthInfo;
import minho.springserver.dto.*;
import minho.springserver.entity.Users;
import minho.springserver.exception.AuthException;
import minho.springserver.infrastructure.auth.UsersRepository;
import minho.springserver.service.AuthService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import javax.transaction.Transactional;
import java.util.*;

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
- @Requestbody
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
    private final UsersRepository usersRepository;
    private final AuthService authService;
    private final AuthApplication authApplication;

    @Autowired
    /* 아래 생성자는 lombok의 @RequiredArgsConstructor로 생략가능합니다. */
    public AuthController(UsersRepository usersRepository, AuthService authService, AuthApplication authApplication) {
        this.usersRepository = usersRepository;
        this.authService = authService;
        this.authApplication = authApplication
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({AuthException.class})
    public ErrorResponse authExceptionHandler(AuthException e) {
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
        AuthCommand.SignUpCommand command = new AuthCommand.SignUpCommand(email, password);
        AuthInfo.SignupInfo user = this.authApplication.signUp(email,password);

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("user signed up");
        successResponse.setData("token");
        return new ResponseEntity<>(successResponse, HttpStatus.CREATED);
    }

    @PostMapping(value = "/api/auth/signin")
    public ResponseEntity<?> postSignIn(HttpServletRequest request, @RequestParam("email") String email, @RequestParam("password") String password) throws AuthException {
        Users user =  this.usersRepository.findByEmail(email).orElse(null);

        if (user == null) {
            throw new AuthException("user does not exists");
        }

        String hash = user.getPassword();
        boolean isMatchPassword = this.authService.checkIsMatchPassword(password, hash);

        if (!isMatchPassword) {
            throw new AuthException("password is invalid");
        }

        HttpSession session = request.getSession();
        session.setAttribute("auth-key", user);

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("user signed in");

        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/api/auth/logout")
    public String postLogout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();

        /* cookie 지우기 (path도 설정) */
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "user logged out" ;
    }

    @GetMapping(value = "/api/auth/me")
    public ResponseEntity<?> getMe(@SessionAttribute(name = "auth-key", required = false) Users user) {
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("my information");
        successResponse.setData(user);

        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    /* BidingResult를 인자로 넘겨주면, controller가 실행됩니다. */
    @PatchMapping(value = "/api/auth/password")
    public SuccessResponse patchPassword(@Validated @ModelAttribute PatchPasswordForm patchPasswordForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            System.out.println((bindingResult.getAllErrors()));
        }
        String currentPassowrd = patchPasswordForm.getCurrentPassword();
        String newPassword = patchPasswordForm.getNewPassword();
        System.out.println("/api/auth/password");
        SuccessResponse successResponse = new SuccessResponse();
        return successResponse;
    }

    @DeleteMapping(value = "/api/auth/signout")
    public String deleteSignOut() {
        String name = "Spring";
        log.info("info log={}", name);
        return "ok" ;
    }
}
