package czachor.jakub.rooms.dao;

import czachor.jakub.rooms.models.Statistics;

import java.util.List;

public interface StatisticsDao {
    Statistics getActiveUsersStat();
    Statistics getTotalUsersJoinedStat();
    List<Statistics> getAllStatistics();
}
