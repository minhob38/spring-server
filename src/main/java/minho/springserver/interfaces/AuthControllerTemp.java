package minho.springserver.interfaces;

import minho.springserver.application.auth.AuthApplication;
import minho.springserver.domain.auth.AuthCommand;
import minho.springserver.domain.auth.AuthInfo;
import minho.springserver.dto.*;
import minho.springserver.exception.AuthException;
import minho.springserver.infrastructure.auth.UsersRepository;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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
public class AuthControllerTemp {
    private final Logger log = LoggerFactory.getLogger((getClass()));
    private final UsersRepository usersRepository;
    private final AuthApplication authApplication;

    @Autowired
    /* 아래 생성자는 lombok의 @RequiredArgsConstructor로 생략가능합니다. */
    public AuthControllerTemp(UsersRepository usersRepository, AuthApplication authApplication) {
        this.usersRepository = usersRepository;
        this.authApplication = authApplication;
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
        AuthInfo.SignupInfo userId = this.authApplication.signUp(command);

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("user signed up");
        successResponse.setData("token");
        return new ResponseEntity<>(successResponse, HttpStatus.CREATED);
    }


}
