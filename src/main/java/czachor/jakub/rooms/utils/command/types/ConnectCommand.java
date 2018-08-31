package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.command.Message;

import java.util.List;

public class ConnectCommand extends Command {
    public ConnectCommand(String author, List<String> details) {
        super(author, details);
        setType(CommandType.CONNECT);
    }

    @Override
    public Message process() {
        //TODO
        return null;
    }
}
