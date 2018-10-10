package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.dao.RoomDao;
import czachor.jakub.rooms.dao.SignatureDao;
import czachor.jakub.rooms.dao.UserDao;
import czachor.jakub.rooms.models.Room;
import czachor.jakub.rooms.models.Signature;
import czachor.jakub.rooms.models.User;
import czachor.jakub.rooms.utils.annotation.Command;
import czachor.jakub.rooms.utils.command.AbstractCommand;
import czachor.jakub.rooms.utils.message.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Command(maxParameters = 1, name = "addsignature", beans = {RoomDao.class, SignatureDao.class, UserDao.class})
public class AddSignatureCommand extends AbstractCommand {

    @Override
    public List<Message> process(MessageProcessHelper helper) {
        Room room = roomDao.getRoomByKey(helper.getRoomKey());
        Signature signature = new Signature();
        signature.setContent(firstParam());
        signature.setRoom(room);
        User user = userDao.findUserByNickname(helper.getUser().getUsername());
        if(user == null){
            user = new User();
            user.setNickname(helper.getUser().getUsername());
            user.setPoints(3);
            userDao.addUser(user);
        }
        return new MessageBuilder()
                .from(Consts.BOT_NAME)
                .type(MessageType.COMMAND)
                .target(Destination.Target.USER)
                .targetName(helper.getSessionId())
                .line("Signature: " + StringUtils.wrap(firstParam(), '"') + " added.")
                .buildAsSingletonList();
    }
}
