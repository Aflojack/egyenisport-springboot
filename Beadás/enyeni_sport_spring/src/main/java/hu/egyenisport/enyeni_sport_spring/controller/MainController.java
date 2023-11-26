package hu.egyenisport.enyeni_sport_spring.controller;

import hu.egyenisport.enyeni_sport_spring.dao.CompetitorDAO;
import hu.egyenisport.enyeni_sport_spring.model.MenuModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    CompetitorDAO competitorDAO;

    @GetMapping("/")
    public String mainPage(Model model, HttpSession session){
        List<MenuModel> menuElements=new ArrayList<>();
        menuElements.add(new MenuModel("Főoldal","/"));
        if(session==null || session.getAttribute("felhasznalonev")==null){
            menuElements.add(new MenuModel("Bejelentkezés","/login"));
            menuElements.add(new MenuModel("Regisztráció","/register"));
        }else{
            boolean admin=session.getAttribute("admin")!=null && (boolean)session.getAttribute("admin");
            if(admin){
                menuElements.add(new MenuModel("Admin","/admin"));
            }
            if(session.getAttribute("felhasznalonev")!=null && competitorDAO.getCompetitorByUsername((String)session.getAttribute("felhasznalonev"))!=null){
                menuElements.add(new MenuModel("Profil","/profile"));
            }
            menuElements.add(new MenuModel("Statisztika","/stat"));
            menuElements.add(new MenuModel("Kijelentkezés","/logout"));
        }
        model.addAttribute("menuElements",menuElements);
        return "index";
    }
}
