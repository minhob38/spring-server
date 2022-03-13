package minho.springserver.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {
    private final Logger log = LoggerFactory.getLogger((getClass()));

    /* api https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-methods */
    @RequestMapping(value = "/api/auth/signup", method = RequestMethod.POST)
    public String postSignUp() {
        String name = "Spring";
        log.info("info log={}", name);
        return "ok" ;
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
}
