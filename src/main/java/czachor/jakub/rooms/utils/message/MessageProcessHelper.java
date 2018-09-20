package czachor.jakub.rooms.utils.message;

import czachor.jakub.rooms.utils.WebsocketUser;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public class MessageProcessHelper {
    private WebsocketUser user;
    private String roomKey;
    private String sessionId;

    public MessageProcessHelper(WebsocketUser user, String roomKey, String sessionId) {
        this.user = user;
        this.roomKey = roomKey;
        this.sessionId = sessionId;
    }

    public boolean isMessageCommand(Message message){
        return !StringUtils.isEmpty(message.getLine()) && message.getLine().charAt(0) == '/';
    }
}
