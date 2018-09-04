package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.message.Message;

import java.util.List;

public class TipCommand extends Command {
    public TipCommand(String author, List<String> details) {
        super(author, details);
        setType(CommandType.TIP);
    }

    @Override
    public Message process() {
        //TODO
        String tip = "Test line 1 \n Test line 2 \n Test line 3";
        return new Message(tip, author);
    }
}
