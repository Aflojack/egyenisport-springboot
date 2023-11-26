package hu.egyenisport.enyeni_sport_spring.controller;

import hu.egyenisport.enyeni_sport_spring.dao.ChampionshipDAO;
import hu.egyenisport.enyeni_sport_spring.dao.CompetitorDAO;
import hu.egyenisport.enyeni_sport_spring.dao.MatchDAO;
import hu.egyenisport.enyeni_sport_spring.dao.UserDAO;
import hu.egyenisport.enyeni_sport_spring.model.ChampionshipModel;
import hu.egyenisport.enyeni_sport_spring.model.CompetitorModel;
import hu.egyenisport.enyeni_sport_spring.model.MatchModel;
import hu.egyenisport.enyeni_sport_spring.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    CompetitorDAO competitorDAO;
    @Autowired
    UserDAO userDAO;
    @Autowired
    ChampionshipDAO championshipDAO;
    @Autowired
    MatchDAO matchDAO;

    @GetMapping("/admin")
    public String adminLoad(Model model){
        List<CompetitorModel> competitors=competitorDAO.listAllCompetitors();
        model.addAttribute("competitors",competitors);
        List<UserModel> users=userDAO.listFreeUsernames();
        model.addAttribute("usernames",users);
        List<ChampionshipModel> championships=championshipDAO.listChampionships();
        model.addAttribute("championships",championships);
        List<MatchModel> matches=matchDAO.listMatch();
        model.addAttribute("matches",matches);
        return "admin";
    }

    @PostMapping("admin/competitor/delete/{versenyzoid}")
    public String deleteCompetitor(@PathVariable int versenyzoid){
        competitorDAO.deleteCompetitor(versenyzoid);
        return "redirect:/admin";
    }

    @PostMapping("/admin/competitor/create")
    public String createComepetitor(
            @RequestParam("nev") String nev,
            @RequestParam("szuletesidatum") String szuletesidatum,
            @RequestParam("szuletesihely") String szuletesihely,
            @RequestParam("allampolgarsag") String allampolgarsag,
            @RequestParam("aktiv") String aktiv,
            @RequestParam("felhasznalonev") String felhasznalonev
    ){
        boolean fiokAllapot="nincs".equals(felhasznalonev);
        CompetitorModel competitor=new CompetitorModel(nev,Date.valueOf(szuletesidatum),szuletesihely,allampolgarsag,"1".equals(aktiv),0.0,fiokAllapot?null:felhasznalonev);
        competitorDAO.createCompetitor(competitor,!fiokAllapot);
        return "redirect:/admin";
    }

    @PostMapping("/admin/championship/create")
    public String createComepetitor(
            @RequestParam("nev") String nev,
            @RequestParam("kezdet") String kezdet,
            @RequestParam("vege") String vege,
            @RequestParam("helyszin") String helyszin,
            @RequestParam("nyilt") String nyilt
    ){
        boolean nyiltBoolean="1".equals(nyilt);
        ChampionshipModel championshipModel=new ChampionshipModel(nev,Date.valueOf(kezdet),Date.valueOf(vege),helyszin,nyiltBoolean);
        championshipDAO.createChampionship(championshipModel);
        return "redirect:/admin";
    }

    @PostMapping("admin/championship/delete/{nev}")
    public String deleteCompetitor(@PathVariable String nev){
        championshipDAO.deleteChampionship(nev);
        return "redirect:/admin";
    }

    @PostMapping("/admin/match/create")
    public String createMatch(
            @RequestParam("datum") String datum,
            @RequestParam("helyszin") String helyszin,
            @RequestParam("bajnoksagnev") String bajnoksagnev
    ){
        MatchModel matchModel=new MatchModel(Date.valueOf(datum),helyszin,bajnoksagnev);
        matchDAO.createMatch(matchModel);
        return "redirect:/admin";
    }

    @PostMapping("admin/match/delete/{id}")
    public String deleteMatch(@PathVariable int id){
        matchDAO.deleteMatch(id);
        return "redirect:/admin";
    }
}
