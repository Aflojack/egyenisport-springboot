package hu.egyenisport.enyeni_sport_spring.controller;

import hu.egyenisport.enyeni_sport_spring.dao.CompetitorDAO;
import hu.egyenisport.enyeni_sport_spring.model.CompetitorModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    CompetitorDAO competitorDAO;

    @GetMapping("/admin")
    public String adminLoad(Model model){
        List<CompetitorModel> competitors=competitorDAO.listAllCompetitors();
        model.addAttribute("competitors",competitors);
        return "admin";
    }

    @PostMapping("admin/deletecompetitor/{versenyzoid}")
    public String deleteCompetitor(@PathVariable int versenyzoid){
        competitorDAO.deleteCompetitor(versenyzoid);
        return "redirect:/admin";
    }
}
