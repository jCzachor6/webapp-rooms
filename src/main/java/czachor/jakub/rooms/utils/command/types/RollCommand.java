package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.message.Message;
import czachor.jakub.rooms.utils.message.MessageType;

import java.util.List;
import java.util.Random;

public class RollCommand extends Command {

    public RollCommand(List<String> details) {
        super(details);
        setType(CommandType.USER_SIGNATURES);
    }

    @Override
    public Message process(String from, String roomkey) {
        Message message = new Message(Consts.BOT_NAME, roomkey, MessageType.SPECIAL);
        Random generator = new Random();
        int max = 100;
        int rolled = generator.nextInt(max);
        message.setLine("Rolled " + rolled + " out of " + max);
        return message;
    }
}
