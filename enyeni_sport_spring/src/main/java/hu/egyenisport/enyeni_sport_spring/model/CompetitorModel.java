package hu.egyenisport.enyeni_sport_spring.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class CompetitorModel {
    private int versenyzoid;
    private String nev;
    private Date szuletesidatum;
    private String szuletesihely;
    private String allampolgarsag;
    private boolean aktiv;
    private double gyesvarany;
    private String felhasznalonev;

    public CompetitorModel(String nev, Date szuletesidatum, String szuletesihely, String allampolgarsag, boolean aktiv, double gyesvarany, String felhasznalonev) {
        this.nev = nev;
        this.szuletesidatum = szuletesidatum;
        this.szuletesihely = szuletesihely;
        this.allampolgarsag = allampolgarsag;
        this.aktiv = aktiv;
        this.gyesvarany = gyesvarany;
        this.felhasznalonev = felhasznalonev;
    }

    public CompetitorModel(int versenyzoid, String nev, Date szuletesidatum, String szuletesihely, String allampolgarsag, boolean aktiv, double gyesvarany, String felhasznalonev) {
        this.versenyzoid = versenyzoid;
        this.nev = nev;
        this.szuletesidatum = szuletesidatum;
        this.szuletesihely = szuletesihely;
        this.allampolgarsag = allampolgarsag;
        this.aktiv = aktiv;
        this.gyesvarany = gyesvarany;
        this.felhasznalonev = felhasznalonev;
    }

    public CompetitorModel(String nev, Date szuletesidatum, String szuletesihely, String allampolgarsag, boolean aktiv, String felhasznalonev) {
        this.nev = nev;
        this.szuletesidatum = szuletesidatum;
        this.szuletesihely = szuletesihely;
        this.allampolgarsag = allampolgarsag;
        this.aktiv = aktiv;
        this.felhasznalonev = felhasznalonev;
    }

    public String aktiv(){
        return aktiv?"igen":"nem";
    }

    public String felhasznalonev(){
        return felhasznalonev==null?"nincs":felhasznalonev;
    }
}
