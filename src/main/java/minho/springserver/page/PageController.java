package minho.springserver.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;

@Controller
public class PageController {
    @GetMapping(value = "/home")
    public ModelAndView pageHome() throws IOException {
        ModelAndView mav = new ModelAndView("home").addObject("data", "hello!");
        return mav;
    }

    @GetMapping(value = "/me")
    public String pageMe(Model model) throws IOException {
        model.addAttribute("data", "hello!!");
        return "me"; // view의 논리이름입니다.
    }
}

// @Controller vs @RestController -> @Controller는 view를 찾음