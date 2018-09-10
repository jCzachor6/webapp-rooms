package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.dao.UserDao;
import czachor.jakub.rooms.models.User;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.message.Message;
import czachor.jakub.rooms.utils.message.MessageType;

import java.util.List;

public class UserInfoCommand extends Command {
    private final UserDao userDao;

    public UserInfoCommand(List<String> details, UserDao userDao) {
        super(CommandType.USER_INFO, details);
        this.userDao = userDao;
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
        Message message = new Message(Consts.BOT_NAME, roomkey, MessageType.COMMAND);
        if (user != null) {
            message.setLine(user.getId() + ". " + user.getNickname() + ", points:" + user.getPoints());
        } else {
            message.setLine("User \"" + details.get(0) + "\" does't exist. ");
        }
        return message;
    }
}
