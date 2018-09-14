package czachor.jakub.rooms.utils.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Destination {
    private Target target;
    private String targetName;

    public Destination(Target target, String targetName) {
        this.target = target;
        this.targetName = targetName;
    }

    public enum Target {
        ROOM,
        USER
    }
}
