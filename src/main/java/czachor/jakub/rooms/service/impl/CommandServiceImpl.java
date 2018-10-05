package czachor.jakub.rooms.service.impl;

import czachor.jakub.rooms.dao.RoomDao;
import czachor.jakub.rooms.dao.SignatureDao;
import czachor.jakub.rooms.dao.StatisticsDao;
import czachor.jakub.rooms.dao.UserDao;
import czachor.jakub.rooms.exceptions.NoSuchCommandException;
import czachor.jakub.rooms.service.CommandService;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandDetailsLoader;
import czachor.jakub.rooms.utils.command.types.*;
import czachor.jakub.rooms.utils.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commandService")
public class CommandServiceImpl implements CommandService {
    private final RoomDao roomDao;
    private final SignatureDao signatureDao;
    private final UserDao userDao;
    private final StatisticsDao statisticsDao;

    @Autowired
    public CommandServiceImpl(RoomDao roomDao, SignatureDao signatureDao, UserDao userDao, StatisticsDao statisticsDao) {
        this.roomDao = roomDao;
        this.signatureDao = signatureDao;
        this.userDao = userDao;
        this.statisticsDao = statisticsDao;
    }

    @Override
    public Command resolve(Message message) {
        return retrieveSpecificCommand(message);
    }

    private Command retrieveSpecificCommand(Message message) {
        CommandDetailsLoader loader = new CommandDetailsLoader(message.getLine());
        switch (loader.getCommand().toLowerCase()) {
            case "addsignature":
                return new AddSignatureCommand(loader, signatureDao, roomDao, userDao);
            case "allusers":
                return new AllUsersCommand(loader, userDao);
            case "connect":
                return new ConnectCommand(loader, statisticsDao);
            case "echo":
                return new EchoCommand(loader);
            case "roll":
                return new RollCommand(loader);
            case "roomsignatures":
                return new RoomSignaturesCommand(loader, signatureDao);
            case "help":
                return new TipCommand(loader);
            case "userinfo":
                return new UserInfoCommand(loader, userDao);
            case "usersignatures":
                return new UserSignaturesCommand(loader, signatureDao);
        }
        throw new NoSuchCommandException("Command \'" + loader.getCommand() + "\' doesn't exist");
    }
}
