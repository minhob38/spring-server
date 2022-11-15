package minho.springserver.controller;

import minho.springserver.dao.UsersRepository;
import minho.springserver.dto.*;
import minho.springserver.dao.UserRepository;
import minho.springserver.entity.Users;
import minho.springserver.service.AuthService;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
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
    private final UserRepository userRepository;
    private final UsersRepository usersRepository;
    private final AuthService authService;

    @Autowired
    /* 아래 생성자는 lombok의 @RequiredArgsConstructor로 생략가능합니다. */
    public AuthController(UserRepository userRepository, UsersRepository usersRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.usersRepository = usersRepository;
        this.authService = authService;
    }

    /* api
    https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-requestmapping
    https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-methods
    */
    @RequestMapping(value = "/api/auth/signup", method = RequestMethod.POST)
    public ResponseEntity<?> postSignUp(HttpServletRequest request, HttpServletResponse response) {
        log.info("info api log={}", "/api/auth/signup");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Optional<Users> user = this.usersRepository.findByEmail(email);

        if (user.isPresent()) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("user already exists");
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }

        String hash = this.authService.createHash(password);
        this.usersRepository.saveUser(email, hash);

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("user signed up");
        successResponse.setData("token");
        return new ResponseEntity<>(successResponse, HttpStatus.CREATED);
    }

    @PostMapping(value = "/api/auth/signin")
    public ResponseEntity<?> postSignIn(HttpServletRequest request, @RequestParam("email") String email, @RequestParam("password") String password) {
        Users user =  this.usersRepository.findByEmail(email).orElse(null);

        if (user == null) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("user does not exists");
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }

        String hash = user.getPassword();
        boolean isMatchPassword = this.authService.checkIsMatchPassword(password, hash);

        if (!isMatchPassword) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("password is invalid");
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
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

    @GetMapping
    public String mappingPath(@PathVariable("userId") String userId) {
        log.info("mappingPath userId={}", userId);
        return "ok";
    }


    @GetMapping(value = "/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false) String cookie

    )  {
        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("headerMap={}", headerMap);
        log.info("host={}", host);
        log.info("cookie={}", cookie);

        return "ok";
    }

    @GetMapping(value = "/query1")
    public String query1(HttpServletRequest request, HttpServletResponse response)  {
        String email = request.getParameter("email"); //query parameter

        log.info("email={}", email);


//        response.getWriter().write("ok");
        return "ok";

    }

    @GetMapping(value = "/query2")
    public String query2(@RequestParam("email") String email)  {
        log.info("email={}", email);


//        response.getWriter().write("ok");
        return "ok";

    }


    @GetMapping(value = "/query3")
    public String query3(@RequestParam Map<String, Object> paramMap)  {
        log.info("email={}", paramMap.get("email"));


//        response.getWriter().write("ok");
        return "ok";

    }

    // model attripute
}
