package hu.egyenisport.enyeni_sport_spring.dao;

import hu.egyenisport.enyeni_sport_spring.model.ParticipateModel;
import hu.egyenisport.enyeni_sport_spring.model.StatModel;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class StatDAO extends JdbcDaoSupport {
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public List<StatModel> getSelection(){
        String sql="SELECT versenyzo.allampolgarsag,COUNT(versenyzo.allampolgarsag) AS db FROM versenyzo,resztvesz,merkozes,bajnoksag WHERE versenyzo.versenyzoid=resztvesz.versenyzoid AND resztvesz.merkozesid=merkozes.merkozesid AND bajnoksag.nev=merkozes.nev AND bajnoksag.nyilt=0 GROUP BY versenyzo.allampolgarsag";
        List <Map< String, Object >> rows = getJdbcTemplate().queryForList(sql);
        List<StatModel> stat=new ArrayList<>();
        if(rows.isEmpty()){
            return stat;
        }
        for (Map< String, Object > row: rows) {
            StatModel statModel=new StatModel(
                    (String)row.get("allampolgarsag"),
                    (long)row.get("db")
            );
            stat.add(statModel);
        }
        return stat;
    }
}
