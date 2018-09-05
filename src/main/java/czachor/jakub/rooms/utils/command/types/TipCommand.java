package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.message.Message;

import java.util.List;

public class TipCommand extends Command {
    public TipCommand(List<String> details) {
        super(details);
        setType(CommandType.TIP);
    }

    @Override
    public Message process(String from, String roomkey) {
        //TODO
        String tip = "Test line 1 \n Test line 2 \n Test line 3";
        return new Message(tip, from, roomkey);
    }
}
