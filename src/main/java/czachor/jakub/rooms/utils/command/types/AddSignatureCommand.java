package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.dao.RoomDao;
import czachor.jakub.rooms.dao.SignatureDao;
import czachor.jakub.rooms.dao.UserDao;
import czachor.jakub.rooms.models.Room;
import czachor.jakub.rooms.models.Signature;
import czachor.jakub.rooms.models.User;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandDetailsLoader;
import czachor.jakub.rooms.utils.message.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class AddSignatureCommand extends Command {
    private final SignatureDao signatureDao;
    private final RoomDao roomDao;
    private final UserDao userDao;

    public AddSignatureCommand(CommandDetailsLoader loader, SignatureDao signatureDao, RoomDao roomDao, UserDao userDao) {
        super(1, loader);
        this.signatureDao = signatureDao;
        this.roomDao = roomDao;
        this.userDao = userDao;
    }

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
