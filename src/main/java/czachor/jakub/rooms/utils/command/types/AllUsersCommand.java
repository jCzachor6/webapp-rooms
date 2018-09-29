package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.dao.UserDao;
import czachor.jakub.rooms.models.User;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandDetailsLoader;
import czachor.jakub.rooms.utils.message.*;

import java.util.List;

public class AllUsersCommand extends Command {
    private final UserDao userDao;

    public AllUsersCommand(CommandDetailsLoader loader, UserDao userDao) {
        super(0, loader);
        this.userDao = userDao;
    }

    @Override
    public List<Message> process(MessageProcessHelper helper) {
        List<User> users = userDao.getUsers();
        MessageBuilder messageBuilder = new MessageBuilder()
                .from(Consts.BOT_NAME)
                .type(MessageType.COMMAND)
                .target(Destination.Target.USER)
                .targetName(helper.getSessionId());
        if (users.isEmpty()) {
            return messageBuilder
                    .line("No users in database. ")
                    .buildAsSingletonList();
        } else {
            StringBuilder stringBuilder = new StringBuilder("List of all users: \n");
            for (int i = 0; i < users.size(); i++) {
                buildIndexedLine(stringBuilder, i, users.get(i).getNickname() + ", points: " + users.get(i).getPoints());
            }
            return messageBuilder.line(stringBuilder.toString()).buildAsSingletonList();
        }
    }
}
