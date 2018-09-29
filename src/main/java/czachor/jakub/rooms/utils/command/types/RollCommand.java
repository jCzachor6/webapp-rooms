package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandDetailsLoader;
import czachor.jakub.rooms.utils.message.*;
import lombok.Getter;

import java.util.List;
import java.util.Random;

@Getter
public class RollCommand extends Command {
    int max = 100;
    int rolled;

    public RollCommand(CommandDetailsLoader loader) {
        super(1, loader);
    }

    @Override
    public List<Message> process(MessageProcessHelper helper) {
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
        this.rolled = generator.nextInt(max) + 1;
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
