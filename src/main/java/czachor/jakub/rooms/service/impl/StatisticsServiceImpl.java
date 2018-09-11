package czachor.jakub.rooms.service.impl;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.dao.StatisticsDao;
import czachor.jakub.rooms.models.Statistics;
import czachor.jakub.rooms.models.dto.StatisticsDTO;
import czachor.jakub.rooms.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "statisticsService")
public class StatisticsServiceImpl implements StatisticsService {
    private final StatisticsDao statisticsDao;

    @Autowired
    public StatisticsServiceImpl(StatisticsDao statisticsDao) {
        this.statisticsDao = statisticsDao;
    }

    @Override
    public StatisticsDTO getActiveUsers() {
        Statistics fromDB = statisticsDao.getActiveUsersStat();
        StatisticsDTO dto = new StatisticsDTO();
        dto.setName(fromDB.getName());
        dto.setValue(fromDB.getIntValue().toString());
        return dto;
    }

    @Override
    public StatisticsDTO getTotalUsersJoined() {
        Statistics fromDB = statisticsDao.getTotalUsersJoinedStat();
        StatisticsDTO dto = new StatisticsDTO();
        dto.setName(fromDB.getName());
        dto.setValue(fromDB.getIntValue().toString());
        return dto;
    }

    @Override
    @Transactional
    public void incrementActiveUsers() {
        Statistics statistics = statisticsDao.getActiveUsersStat();
        int current = statistics.getIntValue();
        statistics.setIntValue(current + 1);
    }

    @Override
    @Transactional
    public void decrementActiveUsers() {
        Statistics statistics = statisticsDao.getActiveUsersStat();
        int current = statistics.getIntValue();
        statistics.setIntValue(current - 1);
    }

    @Override
    @Transactional
    public void incrementTotalJoinedUsers() {
        Statistics statistics = statisticsDao.getTotalUsersJoinedStat();
        int current = statistics.getIntValue();
        statistics.setIntValue(current + 1);
    }

    @Override
    public List<StatisticsDTO> getAllStatistics() {
        List<Statistics> stats = statisticsDao.getAllStatistics();
        List<StatisticsDTO> dtoStats = new ArrayList<>();
        for (Statistics s : stats) {
            StatisticsDTO dto = new StatisticsDTO();
            dto.setName(s.getName());
            String val = s.getStringValue();
            if (val == null) {
                val = s.getIntValue().toString();
            }
            dto.setValue(val);
            dtoStats.add(dto);
        }
        return dtoStats;
    }

    @Override
    public String generateUsername() {
        int number = statisticsDao.getTotalUsersJoinedStat().getIntValue();
        String suffix = String.valueOf(number);
        return Consts.NEW_USER_NAME + suffix;
    }
}
