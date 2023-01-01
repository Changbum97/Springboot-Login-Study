package study.loginstudy.cookie_login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cookie-login")
public class CookieLoginController {

    @GetMapping(value = {"", "/"})
    public String home() {
        return "home";
    }


}
