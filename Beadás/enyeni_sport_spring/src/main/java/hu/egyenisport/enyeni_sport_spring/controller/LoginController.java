package hu.egyenisport.enyeni_sport_spring.controller;

import hu.egyenisport.enyeni_sport_spring.dao.UserDAO;
import hu.egyenisport.enyeni_sport_spring.model.UserModel;
import hu.egyenisport.enyeni_sport_spring.util.PasswordHash;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    UserDAO userDAO;

    @GetMapping("/login")
    public String loginLoad(Model model){
        model.addAttribute("error","");
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(
            Model model,
            HttpSession session,
            @RequestParam("felhasznalonev") String felhasznalonev,
            @RequestParam("jelszo") String jelszo
    ){

            UserModel user=userDAO.getUserByUsername(felhasznalonev);
            if(user==null){
                model.addAttribute("error","A felhasználó nem található! Belépés nem sikeres!");
                return "login";
            }
            if(!PasswordHash.isSamePassword(jelszo,user.getJelszo())){
                model.addAttribute("error","A felhasználó jelszava helytelen! Belépés nem sikeres!");
                return "login";
            }
            session.setAttribute("felhasznalonev",user.getFelhasznalonev());
            session.setAttribute("admin",user.isAdmin());
            return "redirect:/";

    }
}
