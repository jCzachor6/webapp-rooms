package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.dao.UserDao;
import czachor.jakub.rooms.models.User;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.message.*;

import java.util.List;

public class UserInfoCommand extends Command {
    private final UserDao userDao;

    public UserInfoCommand(List<String> details, UserDao userDao) {
        super(CommandType.USER_INFO, details);
        this.userDao = userDao;
    }

    @Override
    public List<Message> process(MessageProcessHelper helper) {
        MessageBuilder builder =
                new MessageBuilder()
                .from(Consts.BOT_NAME)
                .type(MessageType.COMMAND)
                .target(Destination.Target.USER)
                .targetName(helper.getSessionId());
        String nickname;
        if (details.isEmpty()) {
            nickname = helper.getUser().getUsername();
        } else {
            nickname = details.get(0);
        }
        User user = userDao.findUserByNickname(nickname);
        if (user != null) {
            builder.line(user.getId() + ". " + user.getNickname() + ", points:" + user.getPoints());
        } else {
            builder.line("User \"" + details.get(0) + "\" does't exist. ");
        }
        return builder.buildAsSingletonList();
    }
}
