package czachor.jakub.rooms.utils.command;

import lombok.Data;

@Data
public class StringCommand {
    private String line;

    public StringCommand(String line) {
        this.line = line;
    }
}
