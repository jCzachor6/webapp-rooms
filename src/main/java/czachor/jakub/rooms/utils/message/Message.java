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
    private MessageType type;

    public Message(String from, MessageType type) {
        this.line = "";
        this.from = from;
        this.type = type;
    }

    @JsonIgnore
    public boolean isCommand() {
        return !StringUtils.isEmpty(line) && line.charAt(0) == '/';
    }
}
