package czachor.jakub.rooms.utils.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@NoArgsConstructor
public class Message {
    private String from;
    private String line;
    private String roomKey;
    private MessageType type;

    public Message(String from, String roomKey, MessageType type) {
        this.line = "";
        this.from = from;
        this.roomKey = roomKey;
        this.type = type;
    }

    @JsonIgnore
    public boolean isCommand() {
        if (!StringUtils.isEmpty(line)) {
            return line.charAt(0) == '/';
        }
        return false;
    }
}
