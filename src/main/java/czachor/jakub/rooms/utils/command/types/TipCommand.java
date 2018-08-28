package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;

import java.util.List;

public class TipCommand extends Command {
    public TipCommand(String author, List<String> details) {
        super(author, details);
        super.type = CommandType.TIP;
        super.MAX_PARAMETERS = 0;
        super.MIN_PARAMETERS = 0;
        super.throwOnExceedingParameters();
    }
}
