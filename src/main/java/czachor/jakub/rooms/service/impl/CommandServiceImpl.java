package czachor.jakub.rooms.service.impl;

import czachor.jakub.rooms.dao.RoomDao;
import czachor.jakub.rooms.dao.SignatureDao;
import czachor.jakub.rooms.dao.UserDao;
import czachor.jakub.rooms.exceptions.NoSuchCommandException;
import czachor.jakub.rooms.service.CommandService;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.Message;
import czachor.jakub.rooms.utils.command.types.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("commandService")
public class CommandServiceImpl implements CommandService {

    private final RoomDao roomDao;
    private final SignatureDao signatureDao;
    private final UserDao userDao;

    @Autowired
    public CommandServiceImpl(RoomDao roomDao, SignatureDao signatureDao, UserDao userDao) {
        this.roomDao = roomDao;
        this.signatureDao = signatureDao;
        this.userDao = userDao;
    }

    @Override
    public Command resolve(Message message) {
        return retrieveSpecificCommand(message);
    }

    private Command retrieveSpecificCommand(Message message) {
        String afterSlash = message.getLine().substring(1, getIndexOfFirstSpaceOrEnd(message));
        List<String> details = retrieveDetails(message);
        String author = message.getFrom();
        switch (afterSlash.toLowerCase()) {
            case "tip":
            case "help":
            case "info":
            case "?":
            case "??":
            case "???":
                return new TipCommand(author, details);
            case "add":
            case "add_signature":
            case "addsignature":
            case "sign":
                return new AddSignatureCommand(author, details);
            case "users":
            case "allusers":
            case "all_users":
            case "userslist":
            case "users_list":
                return new AllUsersCommand(author, details);
            case "login":
            case "connect":
            case "signin":
            case "sign_in":
                return new ConnectCommand(author, details);
            case "signatures":
            case "roomsignatures":
            case "room_signatures":
                return new RoomSignaturesCommand(author, details);
            case "user":
            case "userinfo":
            case "user_info":
                return new UserInfoCommand(author, details);
            case "usersignatures":
            case "user_signatures":
                return new UserSignaturesCommand(author, details, signatureDao);
            case "echo":
                return new EchoCommand(author, details);
        }
        throw new NoSuchCommandException("Command \'" + afterSlash + "\' doesn't exist");
    }

    private List<String> retrieveDetails(Message message) {
        String[] details = message.getLine().split("\"");
        List<String> detailsNoWhitespaces = new ArrayList<>();
        for (int i = 1 /*ignore first*/; i<details.length; i++) {
            boolean shouldAdd = !StringUtils.isWhitespace(details[i]);
            if (shouldAdd) {
                detailsNoWhitespaces.add(details[i]);
            }
        }
        return detailsNoWhitespaces;
    }

    private int getIndexOfFirstSpaceOrEnd(Message message){
        String s = message.getLine();
        if(s.contains(" ")){
            return s.indexOf(" ");
        }else{
            return s.length();
        }
    }
}
