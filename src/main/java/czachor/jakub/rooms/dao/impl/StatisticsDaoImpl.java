package czachor.jakub.rooms.dao.impl;

import czachor.jakub.rooms.dao.AbstractDao;
import czachor.jakub.rooms.dao.StatisticsDao;
import czachor.jakub.rooms.models.Statistics;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository("statisticsDao")
public class StatisticsDaoImpl extends AbstractDao<Long, Statistics>  implements StatisticsDao {
    @Override
    public Statistics getActiveUsersStat() {
        return getStat("ACTIVE_USERS");
    }

    @Override
    public Statistics getTotalUsersJoinedStat() {
        return getStat("TOTAL_USERS");
    }

    @Override
    public List<Statistics> getAllStatistics() {
        return getAll();
    }

    private Statistics getStat(String statName){
        Query query =  createQuery("from Statistics where NAME =:statName");
        query.setParameter("statName", statName);
        return (Statistics) query.uniqueResult();
    }
}
