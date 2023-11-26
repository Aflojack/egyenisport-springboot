package hu.egyenisport.enyeni_sport_spring.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class MatchModel {
    private int merkozesid;
    private Date datum;
    private String helyszin;
    private String nev;

    public MatchModel(int merkozesid, Date datum, String helyszin, String nev) {
        this.merkozesid = merkozesid;
        this.datum = datum;
        this.helyszin = helyszin;
        this.nev = nev;
    }

    public MatchModel(Date datum, String helyszin, String nev) {
        this.datum = datum;
        this.helyszin = helyszin;
        this.nev = nev;
    }
}
