package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;

import java.util.List;

public class TipCommand extends Command {
    public TipCommand(String author, List<String> details) {
        super(author, details);
        setType(CommandType.TIP);
    }
}
