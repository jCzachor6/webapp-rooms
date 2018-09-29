package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.dao.StatisticsDao;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandDetailsLoader;
import czachor.jakub.rooms.utils.message.*;

import java.util.List;

public class ConnectCommand extends Command {
    private StatisticsDao statisticsDao;

    public ConnectCommand(CommandDetailsLoader loader, StatisticsDao statisticsDao) {
        super(1, loader);
        this.statisticsDao = statisticsDao;
    }

    @Override
    public List<Message> process(MessageProcessHelper helper) {
        if (firstParam().equals("")) {
            return onEmptyDetails(helper);
        } else {
            return newNickname(helper);
        }
    }

    private List<Message> onEmptyDetails(MessageProcessHelper helper) {
        helper.getUser().generate(statisticsDao);
        MessageBuilder builder = new MessageBuilder()
                .from(Consts.BOT_NAME)
                .type(MessageType.JOIN)
                .target(Destination.Target.ROOM)
                .targetName(helper.getRoomKey())
                .line("Hello there! " + helper.getUser().getUsername());
        List<Message> messages = builder.buildAsSingletonList();
        Message secondMessage = builder.target(Destination.Target.USER)
                .targetName(helper.getSessionId())
                .line("Type '/help' to see possible commands. ")
                .type(MessageType.COMMAND)
                .build();
        messages.add(secondMessage);
        return messages;
    }

    private List<Message> newNickname(MessageProcessHelper helper) {
        String line = helper.getUser().getUsername() + " changed his nickname to " + firstParam() + ". ";
        helper.getUser().changeUsername(firstParam());
        return new MessageBuilder()
                .from(Consts.BOT_NAME)
                .type(MessageType.JOIN)
                .target(Destination.Target.ROOM)
                .targetName(helper.getRoomKey())
                .line(line)
                .buildAsSingletonList();
    }
}
