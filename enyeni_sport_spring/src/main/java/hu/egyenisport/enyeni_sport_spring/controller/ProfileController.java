package hu.egyenisport.enyeni_sport_spring.controller;

import hu.egyenisport.enyeni_sport_spring.dao.CompetitorDAO;
import hu.egyenisport.enyeni_sport_spring.dao.UserDAO;
import hu.egyenisport.enyeni_sport_spring.model.CompetitorModel;
import hu.egyenisport.enyeni_sport_spring.model.UserModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    @Autowired
    CompetitorDAO competitorDAO;

    @GetMapping("/profile")
    public String profileLoad(HttpSession session, Model model){
        if(session==null){
            return "redirect:/login";
        }
        String aktualisFelhasznalo=(String)session.getAttribute("felhasznalonev");
        if(aktualisFelhasznalo==null){
            return "redirect:/login";
        }
        CompetitorModel competitorModel=competitorDAO.getCompetitorByUsername(aktualisFelhasznalo);
        if(competitorModel!=null){
            model.addAttribute("currentUser",competitorModel);
            return "profile";
        }
        return "redirect:/";
    }
}
