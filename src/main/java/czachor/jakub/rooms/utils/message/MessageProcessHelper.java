package czachor.jakub.rooms.utils.message;

import czachor.jakub.rooms.utils.WebsocketUser;
import lombok.Getter;
import lombok.Setter;



@Getter
public class MessageProcessHelper {
    private WebsocketUser user;
    private String roomKey;
    private String sessionId;
    @Setter
    private Destination destination;

    public MessageProcessHelper(WebsocketUser user, String roomKey, String sessionId) {
        this.user = user;
        this.roomKey = roomKey;
        this.sessionId = sessionId;
        //default message destination
        this.destination = new Destination(Destination.Target.ROOM, roomKey);
    }

    public void sendBackToUser(){
        this.destination.setTarget(Destination.Target.USER);
        this.destination.setTargetName(sessionId);
    }
}
