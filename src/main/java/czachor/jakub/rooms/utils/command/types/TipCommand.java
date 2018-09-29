package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandDetailsLoader;
import czachor.jakub.rooms.utils.message.*;

import java.util.List;

public class TipCommand extends Command {
    public TipCommand(CommandDetailsLoader loader) {
        super(1, loader);
    }

    @Override
    public List<Message> process(MessageProcessHelper helper) {
        StringBuilder tip = new StringBuilder("List of commands: ");
        Consts.WORKING_COMMANDS.forEach(s -> {
            tip.append("\n");
            tip.append(s);
        });
        return new MessageBuilder()
                .from(Consts.BOT_NAME)
                .type(MessageType.COMMAND)
                .target(Destination.Target.USER)
                .targetName(helper.getSessionId())
                .line(tip.toString())
                .buildAsSingletonList();
    }
}
