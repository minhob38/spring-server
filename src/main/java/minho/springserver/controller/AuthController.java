package minho.springserver.controller;

import minho.springserver.dao.UsersRepository;
import minho.springserver.dto.*;
import minho.springserver.dao.UserRepository;
import minho.springserver.entity.Users;
import minho.springserver.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/*
[Request]
* query / form 요청
- request.getParameter - (HttpServletRequest instance)
- @RequestParam("key")
- @RequestParam Map<String, Object>
- model attribute
* path parameter 요청
-

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
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Optional<Users> user = this.usersRepository.findByEmail(email);

        System.out.println("!!!!!");
        System.out.println(user == null);

        if (user.isPresent()) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("user already exists");
            System.out.println(errorResponse);
            return new ResponseEntity<>(errorResponse, HttpStatus.CREATED);
        }

        String hash = this.authService.createHash(password);
        this.usersRepository.saveUser(email, hash);

        String token = this.authService.createToken(email);
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("user signed up");
        successResponse.setData("token");
        return new ResponseEntity<>(successResponse, HttpStatus.CREATED);
//        User user = new User();
//        user.setEmail(email);
//        user.setPassword(password);
//        userRepository.save(user);
//        List<User> users = userRepository.findAll();
//        log.info("info log={}", email);
//        log.info("info log={}", users);
//        System.out.println("@@@@@");
//        System.out.println(users);
//
////        List<Users> _users = usersRepository.findAll();
//        Users _users = usersRepository.findById();
//        log.info("info _users log={}", _users);
    }

    @PostMapping(value = "/api/auth/signin")
    public SuccessResponse postSignIn(@RequestParam("email") String email, @RequestParam("password") String password) {
        AccessToken accessToken = new AccessToken();
        accessToken.setAccessToken("token...");
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setMessage("user signed in");
        successResponse.setData(accessToken);
        return successResponse;
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
