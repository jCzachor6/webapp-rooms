package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.message.Message;
import czachor.jakub.rooms.utils.message.MessageType;

import java.util.List;

public class TipCommand extends Command {
    public TipCommand(List<String> details) {
        super(details);
        setType(CommandType.TIP);
    }

    @Override
    public Message process(String from, String roomkey) {
        String tip = "Test line 1 \n Test line 2 \n Test line 3";
        Message message = new Message(Consts.BOT_NAME, roomkey, MessageType.COMMAND);
        message.setLine(tip);
        return message;
    }
}
