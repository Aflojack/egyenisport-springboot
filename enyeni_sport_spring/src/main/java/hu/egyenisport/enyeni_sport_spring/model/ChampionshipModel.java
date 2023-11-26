package hu.egyenisport.enyeni_sport_spring.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ChampionshipModel {
    private String nev;
    private Date kezdodatum;
    private Date vegzodatum;
    private String helyszin;
    private boolean nyilt;

    public ChampionshipModel(String nev, Date kezdodatum, Date vegzodatum, String helyszin, boolean nyilt) {
        this.nev = nev;
        this.kezdodatum = kezdodatum;
        this.vegzodatum = vegzodatum;
        this.helyszin = helyszin;
        this.nyilt = nyilt;
    }

    public String nyilt(){
        return nyilt?"bárki nevezhet":"meghívásos";
    }
}
