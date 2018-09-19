package czachor.jakub.rooms.utils.message;

import czachor.jakub.rooms.utils.WebsocketUser;
import lombok.Getter;
import lombok.Setter;



@Getter
public class MessageProcessHelper {
    private WebsocketUser user;
    private String roomKey;
    @Setter
    private Destination destination;

    public MessageProcessHelper(WebsocketUser user, String roomKey) {
        this.user = user;
        this.roomKey = roomKey;
        //default message destination
        this.destination = new Destination(Destination.Target.ROOM, roomKey);
    }

    public void sendBackToUser(){
        String self = user.getUsername();
        this.destination.setTarget(Destination.Target.USER);
        this.destination.setTargetName(self);
    }
}
