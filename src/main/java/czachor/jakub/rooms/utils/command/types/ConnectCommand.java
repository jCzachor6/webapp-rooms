package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.dao.StatisticsDao;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.message.*;

import java.util.List;

public class ConnectCommand extends Command {
    private StatisticsDao statisticsDao;

    public ConnectCommand(List<String> details, StatisticsDao statisticsDao) {
        super(CommandType.CONNECT, details);
        this.statisticsDao = statisticsDao;
    }

    @Override
    public List<Message> process(MessageProcessHelper helper) {
        if (details.isEmpty()) {
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
                .line("Hello there! " + helper.getUser().getUsername() + "\n Type '/help' to see possible commands. ");
        List<Message> messages = builder.buildAsSingletonList();
        Message next = builder.target(Destination.Target.USER)
                .targetName(helper.getSessionId())
                .line("Type '/help' to see possible commands. ")
                .build();

        messages.add(next);
        return messages;
    }

    private List<Message> newNickname(MessageProcessHelper helper) {
        String newNickname = details.get(0);
        helper.getUser().changeUsername(newNickname);
        String line = helper.getUser().getUsername() + " changed his nickname to " + newNickname + ". ";
        return new MessageBuilder()
                .from(Consts.BOT_NAME)
                .type(MessageType.JOIN)
                .target(Destination.Target.ROOM)
                .targetName(helper.getRoomKey())
                .line(line)
                .buildAsSingletonList();
    }
}
