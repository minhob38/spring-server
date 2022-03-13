package minho.springserver.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    private final Logger log = LoggerFactory.getLogger((getClass()));

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

}
