package hu.egyenisport.enyeni_sport_spring.dao;

import hu.egyenisport.enyeni_sport_spring.model.CompetitorModel;
import hu.egyenisport.enyeni_sport_spring.model.UserModel;
import hu.egyenisport.enyeni_sport_spring.util.PasswordHash;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class UserDAO extends JdbcDaoSupport {
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }
    public void createUser(UserModel userModel){
        String sql = "INSERT INTO felhasznalo(felhasznalonev, jelszo, nev, admin) VALUES (?, ?, ?, ?)";
        getJdbcTemplate().update(sql, new Object[] {
                userModel.getFelhasznalonev(), PasswordHash.getHashedPassword(userModel.getJelszo()),userModel.getNev(),userModel.isAdmin()
        });
    }

    public UserModel getUserByUsername(String felhasznalonev){
        String sql = "SELECT * FROM felhasznalo WHERE felhasznalonev=?";
        List <Map< String, Object >> rows = getJdbcTemplate().queryForList(sql,felhasznalonev);
        if(rows.isEmpty()){
            return null;
        }
        for (Map< String, Object > row: rows) {
            return new UserModel(
                    (String)row.get("felhasznalonev"),
                    (String)row.get("jelszo"),
                    (String)row.get("nev"),
                    (boolean)row.get("admin")
            );
        }
        return null;
    }

    public List<UserModel> listAllUsername(){
        String sql = "SELECT * FROM felhasznalo";
        List <Map< String, Object >> rows = getJdbcTemplate().queryForList(sql);
        if(rows.isEmpty()){
            return null;
        }
        List<UserModel> users=new ArrayList<>();
        for (Map< String, Object > row: rows) {
            users.add(new UserModel((String)row.get("felhasznalonev")));
        }
        return users;
    }

    public List<UserModel> listFreeUsernames(){
        String sql = "SELECT * FROM felhasznalo WHERE felhasznalonev NOT IN (SELECT felhasznalo.felhasznalonev FROM felhasznalo,versenyzo WHERE versenyzo.felhasznalonev=felhasznalo.felhasznalonev)";
        List <Map< String, Object >> rows = getJdbcTemplate().queryForList(sql);
        if(rows.isEmpty()){
            return null;
        }
        List<UserModel> users=new ArrayList<>();
        for (Map< String, Object > row: rows) {
            users.add(new UserModel((String)row.get("felhasznalonev")));
        }
        return users;
    }
}
