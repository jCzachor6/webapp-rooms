package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.message.Message;
import czachor.jakub.rooms.utils.message.MessageProcessHelper;
import czachor.jakub.rooms.utils.message.MessageType;

import java.util.List;

public class EchoCommand extends Command {
    public EchoCommand(List<String> details) {
        super(CommandType.ECHO, details);
    }

    @Override
    public Message process(MessageProcessHelper helper) {
        Message message = new Message(Consts.BOT_NAME, MessageType.COMMAND);
        String echo = "echo: " + details.get(0);
        message.setLine(echo);
        helper.sendBackToUser();
        return message;
    }
}
