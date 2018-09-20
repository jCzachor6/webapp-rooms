package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.message.Message;
import czachor.jakub.rooms.utils.message.MessageProcessHelper;

import java.util.List;

public class AddSignatureCommand extends Command {

    public AddSignatureCommand(List<String> details) {
        super(CommandType.ADD_SIGNATURE, details);
    }

    @Override
    public Message process(MessageProcessHelper helper) {
        //TODO
        return null;
    }
}
