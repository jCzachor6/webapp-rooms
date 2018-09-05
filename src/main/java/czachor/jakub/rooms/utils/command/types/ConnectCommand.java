package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.message.Message;

import java.util.List;

public class ConnectCommand extends Command {
    public ConnectCommand(List<String> details) {
        super(details);
        setType(CommandType.CONNECT);
    }

    @Override
    public Message process(String from, String roomkey) {
        Message message = new Message("Hello there! " + from, from, roomkey);
        return message;
    }
}
