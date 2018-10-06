package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.utils.annotation.Command;
import czachor.jakub.rooms.utils.command.AbstractCommand;
import czachor.jakub.rooms.utils.command.CommandDetailsLoader;
import czachor.jakub.rooms.utils.message.*;
import lombok.Getter;

import java.util.List;
import java.util.Random;

@Command(maxParameters = 1, name = "roll")
public class RollCommand extends AbstractCommand {

    @Override
    public List<Message> process(MessageProcessHelper helper) {
        int max = 100;
        Random generator = new Random();
        if (!firstParam().equals("")) {
            try {
                int value = Integer.parseInt(firstParam());
                max = (value < 0) ? -value : value;
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
        int rolled = generator.nextInt(max) + 1;
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
