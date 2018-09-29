package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandDetailsLoader;
import czachor.jakub.rooms.utils.message.Message;
import czachor.jakub.rooms.utils.message.MessageProcessHelper;

import java.util.List;

public class AddSignatureCommand extends Command {

    public AddSignatureCommand(CommandDetailsLoader loader) {
        super(1, loader);
    }

    @Override
    public List<Message> process(MessageProcessHelper helper) {
        //TODO
        return null;
    }
}
