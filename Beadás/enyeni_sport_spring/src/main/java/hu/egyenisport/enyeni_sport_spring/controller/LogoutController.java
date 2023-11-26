package hu.egyenisport.enyeni_sport_spring.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
    @GetMapping("logout")
    public String logoutUser(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
