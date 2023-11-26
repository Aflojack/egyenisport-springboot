package hu.egyenisport.enyeni_sport_spring.dao;

import hu.egyenisport.enyeni_sport_spring.model.MatchModel;
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
public class MatchDAO extends JdbcDaoSupport {
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public void createMatch(MatchModel matchModel){
        String sql = "INSERT INTO merkozes(datum, helyszin, nev) VALUES (?,?,?)";
        getJdbcTemplate().update(sql, new Object[] {
                matchModel.getDatum(),
                matchModel.getHelyszin(),
                matchModel.getNev()
        });
    }

    public List<MatchModel> listMatch(){
        String sql = "SELECT * FROM merkozes";
        List <Map< String, Object >> rows = getJdbcTemplate().queryForList(sql);
        List<MatchModel> matches=new ArrayList<>();
        if(rows.isEmpty()){
            return matches;
        }
        for (Map< String, Object > row: rows) {
            MatchModel match=new MatchModel(
                    (int)row.get("merkozesid"),
                    (Date)row.get("datum"),
                    (String)row.get("helyszin"),
                    (String)row.get("nev")
            );
            matches.add(match);
        }
        return matches;
    }

    public List<MatchModel> listMatchSameChampionship(String name){
        String sql = "SELECT * FROM merkozes WHERE nev=?";
        List <Map< String, Object >> rows = getJdbcTemplate().queryForList(sql,name);
        List<MatchModel> matches=new ArrayList<>();
        if(rows.isEmpty()){
            return matches;
        }
        for (Map< String, Object > row: rows) {
            MatchModel match=new MatchModel(
                    (int)row.get("merkozesid"),
                    (Date)row.get("datum"),
                    (String)row.get("helyszin"),
                    (String)row.get("nev")
            );
            matches.add(match);
        }
        return matches;
    }

    public void deleteMatch(int merkozesid){
        String sql="DELETE FROM merkozes WHERE merkozesid=?";
        getJdbcTemplate().update(sql,new Object[]{
                merkozesid
        });
    }
}
