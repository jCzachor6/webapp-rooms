package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.message.Message;
import czachor.jakub.rooms.utils.message.MessageType;

import java.util.List;

public class EchoCommand extends Command {
    public EchoCommand(List<String> details) {
        super(details);
        setType(CommandType.ECHO);
    }

    @Override
    public Message process(String from, String roomkey) {
        Message message = new Message(Consts.BOT_NAME, roomkey, MessageType.COMMAND);
        String echo = "echo: " + details.get(0);
        message.setLine(echo);
        return message;
    }
}
