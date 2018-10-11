package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.utils.annotation.Command;
import czachor.jakub.rooms.utils.command.AbstractCommand;
import czachor.jakub.rooms.utils.command.CommandDetailsLoader;
import czachor.jakub.rooms.utils.message.*;

import java.util.List;

@Command(maxParameters = 1, name = "echo")
public class EchoCommand extends AbstractCommand {

    @Override
    public List<Message> process(MessageProcessHelper helper) {
        if (firstParam().equals("")) {
            return new MessageBuilder()
                    .from(Consts.BOT_NAME)
                    .type(MessageType.COMMAND)
                    .target(Destination.Target.USER)
                    .targetName(helper.getSessionId())
                    .line("ECHO")
                    .buildAsSingletonList();
        } else {
            return new MessageBuilder()
                    .from(Consts.BOT_NAME)
                    .type(MessageType.COMMAND)
                    .target(Destination.Target.USER)
                    .targetName(helper.getSessionId())
                    .line(firstParam())
                    .buildAsSingletonList();
        }
    }
}
