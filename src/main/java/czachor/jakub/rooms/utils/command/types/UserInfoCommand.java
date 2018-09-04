package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.dao.UserDao;
import czachor.jakub.rooms.models.User;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.message.Message;

import java.util.List;

public class UserInfoCommand extends Command {
    private final UserDao userDao;

    public UserInfoCommand(String author, List<String> details, UserDao userDao) {
        super(author, details);
        this.userDao = userDao;
        setType(CommandType.USER_INFO);
    }

    @Override
    public Message process() {
        User user = userDao.findUserByNickname(who());
        if (user != null) {
            return new Message(user.getId() + ". " + user.getNickname() + ", points:" + user.getPoints(), author);
        } else {
            return new Message("User \"" + details.get(0) + "\" does't exist. ", author);
        }
    }
}
