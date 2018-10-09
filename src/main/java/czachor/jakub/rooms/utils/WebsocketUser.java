package czachor.jakub.rooms.utils;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.dao.StatisticsDao;
import czachor.jakub.rooms.exceptions.UsernameNotAvailableException;
import czachor.jakub.rooms.utils.annotation.SessionUser;
import lombok.Data;

@SessionUser
@Data
public class WebsocketUser {
    private String username;
    private boolean generated;

    public void generate(StatisticsDao statisticsDao) {
        this.username = generateUsername(statisticsDao);
        this.generated = true;
    }

    public void changeUsername(String newUsername, boolean validate) {
        if (validate)
            validateUsername(newUsername);
        this.username = newUsername;
        this.generated = false;
    }

    private String generateUsername(StatisticsDao statisticsDao) {
        int number = statisticsDao.getTotalUsersJoinedStat().getIntValue();
        String suffix = String.valueOf(number);
        return Consts.NEW_USER_NAME + suffix;
    }

    private void validateUsername(String username) {
        if (username.matches(Consts.CHANGE_USER_NAME_REGEX)) {
            throw new UsernameNotAvailableException("You cant change username to " + username);
        }
    }
}
