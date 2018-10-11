package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.utils.annotation.Command;
import czachor.jakub.rooms.utils.command.AbstractCommand;
import czachor.jakub.rooms.utils.message.*;

import java.util.List;
import java.util.Random;

@Command(maxParameters = 0 , name = "flip")
public class FlipCoinCommand extends AbstractCommand {
    private static String[] flipSides = {"HEADS",  "TAILS"};

    @Override
    public List<Message> process(MessageProcessHelper helper) {
        Random generator = new Random();
        int rng = generator.nextInt(2);
        String line = helper.getUser().getUsername() + " flipped " + flipSides[rng] + ". ";
        return new MessageBuilder()
                .from(Consts.BOT_NAME)
                .type(MessageType.SPECIAL)
                .target(Destination.Target.ROOM)
                .targetName(helper.getRoomKey())
                .line(line)
                .buildAsSingletonList();
    }
}
