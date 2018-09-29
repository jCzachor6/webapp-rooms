package czachor.jakub.rooms.service.impl;

import czachor.jakub.rooms.dao.RoomDao;
import czachor.jakub.rooms.dao.SignatureDao;
import czachor.jakub.rooms.dao.StatisticsDao;
import czachor.jakub.rooms.dao.UserDao;
import czachor.jakub.rooms.exceptions.NoSuchCommandException;
import czachor.jakub.rooms.service.CommandService;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandDetailsLoader;
import czachor.jakub.rooms.utils.command.types.ConnectCommand;
import czachor.jakub.rooms.utils.command.types.EchoCommand;
import czachor.jakub.rooms.utils.command.types.RollCommand;
import czachor.jakub.rooms.utils.command.types.TipCommand;
import czachor.jakub.rooms.utils.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            case "tip":
            case "help":
            case "info":
            case "?":
            case "??":
            case "???":
                return new TipCommand(loader);
/*            case "add":
            case "add_signature":
            case "addsignature":
            case "sign":
                return new AddSignatureCommand(details);*/
/*            case "users":
            case "allusers":
            case "all_users":
            case "userslist":
            case "users_list":
                return new AllUsersCommand(details, userDao);*/
            case "login":
            case "connect":
            case "signin":
            case "sign_in":
                return new ConnectCommand(loader, statisticsDao);
/*            case "signatures":
            case "roomsignatures":
            case "room_signatures":
                return new RoomSignaturesCommand(details);*/
/*            case "user":
            case "userinfo":
            case "user_info":
                return new UserInfoCommand(details, userDao);*/
/*            case "usersignatures":
            case "user_signatures":
                return new UserSignaturesCommand(details, signatureDao);*/
            case "echo":
                return new EchoCommand(loader);
            case "roll":
                return new RollCommand(loader);
        }
        throw new NoSuchCommandException("Command \'" + loader.getCommand() + "\' doesn't exist");
    }
}
