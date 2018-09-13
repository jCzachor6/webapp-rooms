package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.dao.UserDao;
import czachor.jakub.rooms.models.User;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.message.Message;
import czachor.jakub.rooms.utils.message.MessageProcessHelper;
import czachor.jakub.rooms.utils.message.MessageType;

import java.util.List;

public class AllUsersCommand extends Command {
    private final UserDao userDao;

    public AllUsersCommand(List<String> details, UserDao userDao) {
        super(CommandType.ALL_USERS, details);
        this.userDao = userDao;
    }

    @Override
    public Message process(MessageProcessHelper helper) {
        List<User> users = userDao.getUsers();
        Message returnMessage = new Message(Consts.BOT_NAME, MessageType.COMMAND);
        if (users.isEmpty()) {
            returnMessage.setLine("No users in database. ");
            return returnMessage;
        } else {
            StringBuilder builder = new StringBuilder("List of all users: \n");
            for (int i = 0; i < users.size(); i++) {
                buildIndexedLine(builder, i, users.get(i).getNickname() + ", points: " + users.get(i).getPoints());
            }
            returnMessage.setLine(builder.toString());
            return returnMessage;
        }
    }
}
