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
            CompetitorModel user=new CompetitorModel(
                    (int)row.get("versenyzoid"),
                    (String)row.get("nev"),
                    (Date)row.get("szuletesidatum"),
                    (String)row.get("szuletesihely"),
                    (String)row.get("allampolgarsag"),
                    (boolean)row.get("aktiv"),
                    (double)row.get("gyesvarany"),
                    (String)row.get("felhasznalonev")
            );
            competitors.add(user);
        }
        return competitors;
    }

    public void deleteCompetitor(int versenyzoid){
        String sql="DELETE FROM versenyzo WHERE versenyzoid=?";
        getJdbcTemplate().update(sql,new Object[]{
                versenyzoid
        });
    }
}
