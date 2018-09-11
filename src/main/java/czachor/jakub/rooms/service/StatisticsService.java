package czachor.jakub.rooms.service;

import czachor.jakub.rooms.models.dto.StatisticsDTO;

import java.util.List;

public interface StatisticsService {
    StatisticsDTO getActiveUsers();
    StatisticsDTO getTotalUsersJoined();
    void incrementActiveUsers();
    void decrementActiveUsers();
    void incrementTotalJoinedUsers();
    List<StatisticsDTO> getAllStatistics();
    String generateUsername();
}
