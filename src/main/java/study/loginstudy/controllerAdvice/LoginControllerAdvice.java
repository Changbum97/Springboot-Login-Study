package study.loginstudy.controllerAdvice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class LoginControllerAdvice {

    @ModelAttribute
    public void addModel(Model model, HttpServletRequest request) {
        String requestUri = request.getRequestURI();

        if (requestUri.contains("api")) {
            return;
        }

        if(requestUri.contains("cookie-login")) {
            model.addAttribute("loginType", "cookie-login");
            model.addAttribute("pageName", "쿠키 로그인");
        } else if(requestUri.contains("session-login")) {
            model.addAttribute("loginType", "session-login");
            model.addAttribute("pageName", "세션 로그인");
        } else if(requestUri.contains("jwt-login")) {
            model.addAttribute("loginType", "jwt-login");
            model.addAttribute("pageName", "Jwt Token 화면 로그인");
        } else if(requestUri.contains("security-login")) {
            model.addAttribute("loginType", "security-login");
            model.addAttribute("pageName", "Security 로그인");
        }
    }
}
