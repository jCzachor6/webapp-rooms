package czachor.jakub.rooms.utils.message;

import czachor.jakub.rooms.utils.CustomPrincipal;
import lombok.Getter;
import lombok.Setter;

@Getter
public class MessageProcessHelper {
    private CustomPrincipal principal;
    private String roomKey;
    @Setter
    private Destination destination;

    public MessageProcessHelper(CustomPrincipal principal, String roomKey) {
        this.principal = principal;
        this.roomKey = roomKey;
        //default message destination
        this.destination = new Destination(Destination.Target.ROOM, roomKey);
    }

    public String getNickname(){
        return principal.getName();
    }
}
