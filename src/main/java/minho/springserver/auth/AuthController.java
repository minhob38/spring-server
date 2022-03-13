package minho.springserver.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    private final Logger log = LoggerFactory.getLogger((getClass()));

    @RequestMapping(value = "/api/auth/signup", method = RequestMethod.POST) //@PostMappring(value="/")
    public String logTest() {
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
