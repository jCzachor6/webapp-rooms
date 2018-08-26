package czachor.jakub.rooms.utils.command;

import lombok.Data;

import java.util.List;

@Data
public class Command {
    private CommandType type;
    private String author;
    private List<String> details;
}
