package hu.egyenisport.enyeni_sport_spring.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserModel {
    private String felhasznalonev;
    private String jelszo;
    private String nev;
    private boolean admin;

    public UserModel(String felhasznalonev, String jelszo, String nev, boolean admin) {
        this.felhasznalonev = felhasznalonev;
        this.jelszo = jelszo;
        this.nev = nev;
        this.admin = admin;
    }

    public UserModel(String felhasznalonev) {
        this.felhasznalonev = felhasznalonev;
    }
}
