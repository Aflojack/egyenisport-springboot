package hu.egyenisport.enyeni_sport_spring.controller;

import hu.egyenisport.enyeni_sport_spring.dao.UserDAO;
import hu.egyenisport.enyeni_sport_spring.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
    @Autowired
    private UserDAO userDAO;

    @GetMapping("/register")
    public String registerLoad(Model model){
        model.addAttribute("error","");
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            Model model,
            @RequestParam("felhasznalonev") String felhasznalonev,
            @RequestParam("jelszo1") String jelszo1,
            @RequestParam("jelszo2") String jelszo2,
            @RequestParam("nev") String nev,
            @RequestParam("admin") String admin
        ){
        try{
            if(jelszo1==null || !jelszo1.equals(jelszo2)){
                model.addAttribute("error","A jelszavak nem egyeznek! Regisztráció nem sikeres!");
                return "register";
            }
            if(userDAO.getUserByUsername(felhasznalonev)!=null){
                model.addAttribute("error","A felhasználónév már foglalt! Regisztráció nem sikeres!");
                return "register";
            }
            userDAO.createUser(new UserModel(felhasznalonev,jelszo1,nev,"1".equals(admin)));
            model.addAttribute("error","Regisztráció sikeres!");
            return "register";
        }catch (Exception ex){
            model.addAttribute("error","Regisztráció nem sikeres!"+ex.getMessage());
        }
        return "register";
    }
}
