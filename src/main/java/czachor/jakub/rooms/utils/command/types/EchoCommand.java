package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.exceptions.TooManyCommandParameters;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.message.*;

import java.util.List;

public class EchoCommand extends Command {
    public EchoCommand(List<String> details) {
        super(CommandType.ECHO, details);
    }

    @Override
    public List<Message> process(MessageProcessHelper helper) {
        int detailsSize = details.size();
        switch (detailsSize){
            case 0:
                return new MessageBuilder()
                        .from(Consts.BOT_NAME)
                        .type(MessageType.COMMAND)
                        .target(Destination.Target.USER)
                        .targetName(helper.getSessionId())
                        .line("ECHO")
                        .buildAsSingletonList();
            case 1:
                String echo = details.get(0);
                return new MessageBuilder()
                        .from(Consts.BOT_NAME)
                        .type(MessageType.COMMAND)
                        .target(Destination.Target.USER)
                        .targetName(helper.getSessionId())
                        .line(echo)
                        .buildAsSingletonList();
                default:
                    throw new TooManyCommandParameters();
        }
    }
}
