package hu.egyenisport.enyeni_sport_spring.dao;

import hu.egyenisport.enyeni_sport_spring.model.CompetitorModel;
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
public class CompetitorDAO  extends JdbcDaoSupport {
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public void createCompetitor(CompetitorModel competitorModel){
        String sql = "INSERT INTO versenyzo(nev, szuletesidatum, szuletesihely, allampolgarsag, aktiv, gyesvarany) VALUES (?,?,?,?,?,?)";
        getJdbcTemplate().update(sql, new Object[] {
                competitorModel.getNev(),
                competitorModel.getSzuletesidatum(),
                competitorModel.getSzuletesihely(),
                competitorModel.getAllampolgarsag(),
                competitorModel.isAktiv(),
                0.0
        });
    }

    public void createCompetitor(CompetitorModel competitorModel, boolean hasAccount){
        if(!hasAccount){
            createCompetitor(competitorModel);
            return;
        }
        String sql = "INSERT INTO versenyzo(nev, szuletesidatum, szuletesihely, allampolgarsag, aktiv, gyesvarany, felhasznalonev) VALUES (?,?,?,?,?,?,?)";
        getJdbcTemplate().update(sql, new Object[] {
                competitorModel.getNev(),
                competitorModel.getSzuletesidatum(),
                competitorModel.getSzuletesihely(),
                competitorModel.getAllampolgarsag(),
                competitorModel.isAktiv(),
                0.0,
                competitorModel.getFelhasznalonev()
        });
    }

    public List<CompetitorModel> listAllCompetitors(){
        String sql = "SELECT * FROM versenyzo";
        List <Map< String, Object >> rows = getJdbcTemplate().queryForList(sql);
        if(rows.isEmpty()){
            return null;
        }
        List<CompetitorModel> competitors=new ArrayList<>();
        for (Map< String, Object > row: rows) {
            CompetitorModel competitor=new CompetitorModel(
                    (int)row.get("versenyzoid"),
                    (String)row.get("nev"),
                    (Date)row.get("szuletesidatum"),
                    (String)row.get("szuletesihely"),
                    (String)row.get("allampolgarsag"),
                    (boolean)row.get("aktiv"),
                    (double)row.get("gyesvarany"),
                    (String)row.get("felhasznalonev")
            );
            competitors.add(competitor);
        }
        return competitors;
    }

    public CompetitorModel getCompetitorByCompetitorid(int competitorid){
        String sql = "SELECT * FROM versenyzo WHERE versenyzoid=?";
        List <Map< String, Object >> rows = getJdbcTemplate().queryForList(sql,competitorid);
        if(rows.isEmpty()){
            return null;
        }
        for (Map< String, Object > row: rows) {
            return new CompetitorModel(
                    (int)row.get("versenyzoid"),
                    (String)row.get("nev"),
                    (Date)row.get("szuletesidatum"),
                    (String)row.get("szuletesihely"),
                    (String)row.get("allampolgarsag"),
                    (boolean)row.get("aktiv"),
                    (double)row.get("gyesvarany"),
                    (String)row.get("felhasznalonev")
            );
        }
        return null;
    }

    public CompetitorModel getCompetitorByUsername(String username){
        String sql = "SELECT * FROM versenyzo WHERE felhasznalonev=?";
        List <Map< String, Object >> rows = getJdbcTemplate().queryForList(sql,username);
        if(rows.isEmpty()){
            return null;
        }
        for (Map< String, Object > row: rows) {
            return new CompetitorModel(
                    (int)row.get("versenyzoid"),
                    (String)row.get("nev"),
                    (Date)row.get("szuletesidatum"),
                    (String)row.get("szuletesihely"),
                    (String)row.get("allampolgarsag"),
                    (boolean)row.get("aktiv"),
                    (double)row.get("gyesvarany"),
                    (String)row.get("felhasznalonev")
            );
        }
        return null;
    }

    public void deleteCompetitor(int versenyzoid){
        String sql="DELETE FROM versenyzo WHERE versenyzoid=?";
        getJdbcTemplate().update(sql,new Object[]{
                versenyzoid
        });
        updateCompetitorStat(versenyzoid);
    }

    public void updateCompetitor(CompetitorModel competitorModel){
        String sql="UPDATE versenyzo SET nev=?, szuletesidatum=?, szuletesihely=?, allampolgarsag=?, aktiv=? WHERE felhasznalonev=?";
        getJdbcTemplate().update(sql,new Object[]{
                competitorModel.getNev(),
                competitorModel.getSzuletesidatum(),
                competitorModel.getSzuletesihely(),
                competitorModel.getAllampolgarsag(),
                competitorModel.isAktiv(),
        });
    }

    public void updateCompetitorStat(int versenyzoid){
        String sql="SELECT COUNT(eredmeny) AS db_gy FROM resztvesz WHERE eredmeny=1 AND versenyzoid=? GROUP BY eredmeny";
        List <Map< String, Object >> rows = getJdbcTemplate().queryForList(sql,versenyzoid);
        long db_gy=0;
        for (Map< String, Object > row: rows) {
            db_gy=(long)row.get("db_gy");
            break;
        }
        sql="SELECT COUNT(eredmeny) AS db_v FROM resztvesz WHERE eredmeny=0 AND versenyzoid=? GROUP BY eredmeny";
        rows = getJdbcTemplate().queryForList(sql,versenyzoid);
        long db_v=0;
        for (Map< String, Object > row: rows) {
            db_v=(long)row.get("db_v");
            break;
        }
        double arany;
        if(db_v==0.0 || db_gy==0.0){
            arany=0.0;
        }else{
            arany=db_gy/(double)db_v;
        }
        sql="UPDATE versenyzo SET gyesvarany=? WHERE versenyzoid=?";
        getJdbcTemplate().update(sql,new Object[]{
                arany,
                versenyzoid
        });
    }
}
