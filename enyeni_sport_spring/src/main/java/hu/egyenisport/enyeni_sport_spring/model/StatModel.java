package hu.egyenisport.enyeni_sport_spring.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatModel {
    private String nev;
    private long db;

    public StatModel(String nev, long db) {
        this.nev = nev;
        this.db = db;
    }
}
