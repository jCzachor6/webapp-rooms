package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.command.Message;

import java.util.List;

public class EchoCommand extends Command {
    public EchoCommand(String author, List<String> details) {
        super(author, details);
        setType(CommandType.ECHO);
    }

    @Override
    public Message process() {
        String echo = "echo: " + details.get(0);
        return new Message(echo, author);
    }
}
