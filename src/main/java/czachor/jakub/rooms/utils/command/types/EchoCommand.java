package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.message.Message;

import java.util.List;

public class EchoCommand extends Command {
    public EchoCommand(List<String> details) {
        super(details);
        setType(CommandType.ECHO);
    }

    @Override
    public Message process(String from, String roomkey) {
        String echo = "echo: " + details.get(0);
        return new Message(echo, from, roomkey);
    }
}
