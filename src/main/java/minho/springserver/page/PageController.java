package minho.springserver.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;

@Controller
public class PageController {
    @GetMapping(value = "/home")
    public String pageHome(Model model) {
        CurrentDateTime currentDateTime = new CurrentDateTime();
        model.addAttribute("current", currentDateTime);
        return "home"; // view의 논리이름입니다.
    }

    @GetMapping(value = "/me")
    public ModelAndView pageMe() {
        ModelAndView mav = new ModelAndView("me").addObject("content","thymeleaf view입니다.");
        return mav;
    }
}

// @Controller vs @RestController -> @Controller는 handler의 반환이 문자열일때, view를 찾음