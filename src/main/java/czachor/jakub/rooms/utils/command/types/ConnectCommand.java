package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.message.Message;
import czachor.jakub.rooms.utils.message.MessageType;

import java.util.List;

public class ConnectCommand extends Command {
    public ConnectCommand(List<String> details) {
        super(details);
        setType(CommandType.CONNECT);
    }

    @Override
    public Message process(String from, String roomkey) {
        Message message = new Message(Consts.BOT_NAME, roomkey, MessageType.JOIN);
        message.setLine("Hello there! " + from + "\n Type '/help' to see possible commands. ");
        return message;
    }
}
