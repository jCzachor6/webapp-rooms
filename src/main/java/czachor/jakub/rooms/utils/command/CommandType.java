package czachor.jakub.rooms.utils.command;

import lombok.Getter;

@Getter
public enum CommandType {
    TIP(0, 0),
    USER_SIGNATURES(0, 0),
    ROOM_SIGNATURES(0, 0),
    ADD_SIGNATURE(0, 0),
    ALL_USERS(0, 0),
    USER_INFO(0, 0);

    private int minParameters;
    private int maxParameters;
    CommandType(int minParameters, int maxParameters) {
        this.minParameters = minParameters;
        this.maxParameters = maxParameters;
    }
}
