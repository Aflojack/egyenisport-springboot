package hu.egyenisport.enyeni_sport_spring.controller;

import hu.egyenisport.enyeni_sport_spring.dao.StatDAO;
import hu.egyenisport.enyeni_sport_spring.model.StatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StatController {
    @Autowired
    StatDAO statDAO;
    @GetMapping("/stat")
    public String statLoad(Model model){
        List<StatModel> list=statDAO.getSelection();
        model.addAttribute("list",list);
        List<String> names=statDAO.getOld();
        model.addAttribute("names",names);
        return "stat";
    }
}
