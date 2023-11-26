package hu.egyenisport.enyeni_sport_spring.dao;

import hu.egyenisport.enyeni_sport_spring.model.MatchModel;
import hu.egyenisport.enyeni_sport_spring.model.ParticipateModel;
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
public class ParticipateDAO extends JdbcDaoSupport {
    @Autowired
    DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    public void addParticipate(ParticipateModel participateModel){
        String sql = "INSERT INTO resztvesz(versenyzoid, merkozesid, eredmeny) VALUES (?,?,?)";
        getJdbcTemplate().update(sql, new Object[] {
                participateModel.getVersenyzoid(),
                participateModel.getMerkozesid(),
                participateModel.isEredmeny()?1:0
        });
    }

    public List<ParticipateModel> list(){
        String sql = "SELECT * FROM resztvesz";
        List <Map< String, Object >> rows = getJdbcTemplate().queryForList(sql);
        List<ParticipateModel> partis=new ArrayList<>();
        if(rows.isEmpty()){
            return partis;
        }
        for (Map< String, Object > row: rows) {
            ParticipateModel part=new ParticipateModel(
                    (int)row.get("resztveszid"),
                    (int)row.get("versenyzoid"),
                    (int)row.get("merkozesid"),
                    (boolean)row.get("eredmeny")
            );
            partis.add(part);
        }
        return partis;
    }

    public void deleteParticipate(int resztveszid){
        String sql="DELETE FROM resztvesz WHERE resztveszid=?";
        getJdbcTemplate().update(sql,new Object[]{
                resztveszid
        });
    }
}
