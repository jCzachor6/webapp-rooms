package czachor.jakub.rooms.utils.command;

import lombok.Getter;

@Getter
public enum CommandType {
    ADD_SIGNATURE(1, 1),
    ALL_USERS(0, 0),
    CONNECT(0,1),
    ECHO(0, 1),
    ROLL(0,1),
    ROOM_SIGNATURES(0, 1),
    TIP(0, 0),
    USER_INFO(0, 1),
    USER_SIGNATURES(0, 1);

    private int minParameters;
    private int maxParameters;
    CommandType(int minParameters, int maxParameters) {
        this.minParameters = minParameters;
        this.maxParameters = maxParameters;
    }
}
