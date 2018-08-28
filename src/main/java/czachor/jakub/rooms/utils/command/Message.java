package czachor.jakub.rooms.utils.command;

import lombok.Data;

@Data
public class Message {
    private String line;
    private String from;

    public Message(String line, String from) {
        this.line = line;
        this.from = from;
    }
}
