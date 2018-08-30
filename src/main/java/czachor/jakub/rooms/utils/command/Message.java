package czachor.jakub.rooms.utils.command;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {
    private String from;
    private String line;

    public Message(String line, String from) {
        this.from = from;
        this.line = line;
    }
}
