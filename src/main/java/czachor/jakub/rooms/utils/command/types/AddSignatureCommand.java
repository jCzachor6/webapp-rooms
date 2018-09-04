package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.message.Message;

import java.util.List;

public class AddSignatureCommand extends Command {

    public AddSignatureCommand(String author, List<String> details) {
        super(author, details);
        setType(CommandType.ADD_SIGNATURE);
    }

    @Override
    public Message process() {
        //TODO
        return null;
    }
}
