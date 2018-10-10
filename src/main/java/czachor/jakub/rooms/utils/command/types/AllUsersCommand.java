package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.dao.UserDao;
import czachor.jakub.rooms.models.User;
import czachor.jakub.rooms.utils.annotation.Command;
import czachor.jakub.rooms.utils.command.AbstractCommand;
import czachor.jakub.rooms.utils.message.*;

import java.util.List;

@Command(maxParameters = 0, name = "allusers", beans = {UserDao.class})
public class AllUsersCommand extends AbstractCommand {

    @Override
    public List<Message> process(MessageProcessHelper helper) {
        List<User> users = getUserDao().getUsers();
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
