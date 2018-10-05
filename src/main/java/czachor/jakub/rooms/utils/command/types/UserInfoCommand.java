package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.dao.UserDao;
import czachor.jakub.rooms.models.User;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandDetailsLoader;
import czachor.jakub.rooms.utils.message.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class UserInfoCommand extends Command {
    private final UserDao userDao;

    public UserInfoCommand(CommandDetailsLoader loader, UserDao userDao) {
        super(1, loader);
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
        if (firstParam().equals("")) {
            nickname = helper.getUser().getUsername();
        } else {
            nickname = firstParam();
        }
        User user = userDao.findUserByNickname(nickname);
        if (user != null) {
            builder.line(user.getId() + ". " + user.getNickname() + ", points:" + user.getPoints());
        } else {
            builder.line("User " + StringUtils.wrap(firstParam(), '"') + " does't exist. ");
        }
        return builder.buildAsSingletonList();
    }
}
