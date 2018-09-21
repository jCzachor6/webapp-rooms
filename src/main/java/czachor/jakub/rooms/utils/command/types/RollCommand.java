package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.message.*;
import lombok.Getter;

import java.util.List;
import java.util.Random;

@Getter
public class RollCommand extends Command {
    int max = 100;
    int rolled;

    public RollCommand(List<String> details) {
        super(CommandType.USER_SIGNATURES, details);
    }

    @Override
    public List<Message> process(MessageProcessHelper helper) {
        Random generator = new Random();
        if (!details.isEmpty()) {
            try {
                int value = Integer.parseInt(details.get(0));
                if (value < 0) {
                    max = -value;
                } else {
                    max = value;
                }
            } catch (NumberFormatException e) {
                return new MessageBuilder()
                        .from(Consts.BOT_NAME)
                        .type(MessageType.COMMAND)
                        .target(Destination.Target.USER)
                        .targetName(helper.getSessionId())
                        .line("Bad input")
                        .buildAsSingletonList();
            }
        }
        this.rolled = generator.nextInt(max);
        String line = helper.getUser().getUsername() + " rolled " + rolled + " out of " + max;
        return new MessageBuilder()
                .from(Consts.BOT_NAME)
                .type(MessageType.SPECIAL)
                .target(Destination.Target.ROOM)
                .targetName(helper.getRoomKey())
                .line(line)
                .buildAsSingletonList();
    }
}
