package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.command.Message;

import java.util.List;

public class UserSignaturesCommand extends Command {
    public UserSignaturesCommand(String author, List<String> details) {
        super(author, details);
        setType(CommandType.USER_SIGNATURES);
    }

    @Override
    public Message process() {
        //TODO
        return null;
    }
}
