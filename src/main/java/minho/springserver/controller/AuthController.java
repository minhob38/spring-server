package minho.springserver.controller;

import minho.springserver.dao.UsersRepository;
import minho.springserver.dto.PostSignupResponse;
import minho.springserver.dto.User;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/*
[Request]
* query / form 요청
- request.getParameter - (HttpServletRequest instance)
- @RequestParam("key")
- @RequestParam Map<String, Object>
model attribute
* path parameter

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
- @RestController는 @RsponseBody를 가지고 있어, view template을 찾지 않습니다.
*/
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
    public ResponseEntity<PostSignupResponse> postSignUp(HttpServletRequest request, HttpServletResponse response) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        System.out.println("======");

        boolean isUser = this.authService.getIsUser();
        PostSignupResponse postSignupResponse = new PostSignupResponse();
        postSignupResponse.setMessage("user already exists");

        if (isUser) {
            System.out.println(postSignupResponse);
            return new ResponseEntity<>(postSignupResponse, HttpStatus.CREATED);
        }
        System.out.println(isUser);
//
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
        return new ResponseEntity<>(postSignupResponse, HttpStatus.CREATED);
    }

    @PostMapping(value = "/api/auth/signin")
    public String postSignIn() {
        String name = "Spring";
        log.info("info log={}", name);
        return "ok" ;
    }

    @PatchMapping(value = "/api/auth/password")
    public String patchPassword() {
        String name = "Spring";
        log.info("info log={}", name);
        return "ok" ;
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
