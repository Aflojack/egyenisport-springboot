package hu.egyenisport.enyeni_sport_spring.dao;

import hu.egyenisport.enyeni_sport_spring.model.ChampionshipModel;
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
public class ChampionshipDAO extends JdbcDaoSupport {
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public void createChampionship(ChampionshipModel championshipModel){
        String sql = "INSERT INTO bajnoksag(nev, kezdodatum, vegzodatum, helyszin, nyilt) VALUES (?,?,?,?,?)";
        getJdbcTemplate().update(sql, new Object[] {
                championshipModel.getNev(),
                championshipModel.getKezdodatum(),
                championshipModel.getVegzodatum(),
                championshipModel.getHelyszin(),
                championshipModel.isNyilt()?1:0
        });
    }

    public List<ChampionshipModel> listChampionships(){
        String sql = "SELECT * FROM bajnoksag";
        List <Map< String, Object >> rows = getJdbcTemplate().queryForList(sql);
        List<ChampionshipModel> championships=new ArrayList<>();
        if(rows.isEmpty()){
            return championships;
        }
        for (Map< String, Object > row: rows) {
            ChampionshipModel championship=new ChampionshipModel(
                    (String)row.get("nev"),
                    (Date)row.get("kezdodatum"),
                    (Date)row.get("vegzodatum"),
                    (String)row.get("helyszin"),
                    (boolean)row.get("nyilt")
            );
            championships.add(championship);
        }
        return championships;
    }

    public void deleteChampionship(String nev){
        String sql="DELETE FROM bajnoksag WHERE nev=?";
        getJdbcTemplate().update(sql,new Object[]{
                nev
        });
    }
}
