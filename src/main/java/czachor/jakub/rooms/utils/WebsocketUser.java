package czachor.jakub.rooms.utils;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.dao.StatisticsDao;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component(value = "user")
@Scope(
        scopeName = "websocket",
        proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class WebsocketUser{
    private String username;
    private boolean generated;
    private String uuid;

    public void generate(StatisticsDao statisticsDao){
        this.username = generateUsername(statisticsDao);
        this.generated = true;
        this.uuid = UUID.randomUUID().toString();
    }

    public void changeUsername(String newUsername){
        //TODO regex for generated username
        this.username = newUsername;
        this.generated = false;
    }

    private String generateUsername(StatisticsDao statisticsDao) {
        int number = statisticsDao.getTotalUsersJoinedStat().getIntValue();
        String suffix = String.valueOf(number);
        return Consts.NEW_USER_NAME + suffix;
    }
}
