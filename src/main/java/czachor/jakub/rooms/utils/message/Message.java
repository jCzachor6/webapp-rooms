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

    public Message(String line, String from) {
        this.line = line;
        this.from = from;
    }

    public Message(String line, String from, String room) {
        this.from = from;
        this.line = line;
        this.roomKey = room;
    }

    @JsonIgnore
    public boolean isCommand() {
        if (!StringUtils.isEmpty(line)) {
            return line.charAt(0) == '/';
        }
        return false;
    }
}
