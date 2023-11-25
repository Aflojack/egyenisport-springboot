package hu.egyenisport.enyeni_sport_spring.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuModel {
    private String name;
    private String pathname;

    public MenuModel(String name, String pathname) {
        this.name = name;
        this.pathname = pathname;
    }
}
