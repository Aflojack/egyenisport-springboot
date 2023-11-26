package hu.egyenisport.enyeni_sport_spring.controller;

import hu.egyenisport.enyeni_sport_spring.dao.CompetitorDAO;
import hu.egyenisport.enyeni_sport_spring.model.CompetitorModel;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

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

    @PostMapping("/profile/update")
    public String profileUpdate(
            @RequestParam("nev") String nev,
            @RequestParam("szuletesidatum") String szuletesidatum,
            @RequestParam("szuletesihely") String szuletesihely,
            @RequestParam("allampolgarsag") String allampolgarsag,
            @RequestParam("aktiv") String aktiv
    ){
        competitorDAO.updateCompetitor(new CompetitorModel(nev, Date.valueOf(szuletesidatum),szuletesihely,allampolgarsag,"1".equals(aktiv)));
        return "redirect:/profile";
    }
}
