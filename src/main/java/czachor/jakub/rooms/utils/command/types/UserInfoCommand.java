package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.dao.UserDao;
import czachor.jakub.rooms.models.User;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.message.Message;

import java.util.List;

public class UserInfoCommand extends Command {
    private final UserDao userDao;

    public UserInfoCommand(List<String> details, UserDao userDao) {
        super(details);
        this.userDao = userDao;
        setType(CommandType.USER_INFO);
    }

    @Override
    public Message process(String from, String roomkey) {
        String nickname;
        if (details.isEmpty()) {
            nickname = from;
        } else {
            nickname = details.get(0);
        }
        User user = userDao.findUserByNickname(nickname);
        if (user != null) {
            return new Message(user.getId() + ". " + user.getNickname() + ", points:" + user.getPoints(), from, roomkey);
        } else {
            return new Message("User \"" + details.get(0) + "\" does't exist. ", from, roomkey);
        }
    }
}
