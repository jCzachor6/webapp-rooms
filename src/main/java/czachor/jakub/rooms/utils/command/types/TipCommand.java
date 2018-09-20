package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.message.Message;
import czachor.jakub.rooms.utils.message.MessageProcessHelper;
import czachor.jakub.rooms.utils.message.MessageType;

import java.util.List;

public class TipCommand extends Command {
    public TipCommand(List<String> details) {
        super(CommandType.TIP, details);
    }

    @Override
    public Message process(MessageProcessHelper helper) {
        StringBuilder tip = new StringBuilder("List of commands: ");
        Consts.WORKING_COMMANDS.forEach(s -> {
            tip.append("\n");
            tip.append(s);
        });
        Message message = new Message(Consts.BOT_NAME, MessageType.COMMAND);
        message.setLine(tip.toString());
        helper.sendBackToUser();
        return message;
    }
}
