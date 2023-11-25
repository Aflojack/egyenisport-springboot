package hu.egyenisport.enyeni_sport_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public String adminLoad(){
        return "admin";
    }
}
