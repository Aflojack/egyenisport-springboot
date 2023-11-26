package hu.egyenisport.enyeni_sport_spring.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticipateModel {
    int resztveszid;
    int versenyzoid;
    int merkozesid;
    boolean eredmeny;

    public ParticipateModel(int resztveszid, int versenyzoid, int merkozesid, boolean eredmeny) {
        this.resztveszid = resztveszid;
        this.versenyzoid = versenyzoid;
        this.merkozesid = merkozesid;
        this.eredmeny = eredmeny;
    }

    public ParticipateModel(int versenyzoid, int merkozesid, boolean eredmeny) {
        this.versenyzoid = versenyzoid;
        this.merkozesid = merkozesid;
        this.eredmeny = eredmeny;
    }

    public String eredmeny(){
        return eredmeny?"Győztes":"Vesztes";
    }
}
