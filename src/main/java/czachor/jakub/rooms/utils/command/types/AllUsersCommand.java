package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.dao.UserDao;
import czachor.jakub.rooms.models.User;
import czachor.jakub.rooms.utils.command.Command;
import czachor.jakub.rooms.utils.command.CommandType;
import czachor.jakub.rooms.utils.command.Message;

import java.util.List;

public class AllUsersCommand extends Command {
    private final UserDao userDao;

    public AllUsersCommand(String author, List<String> details, UserDao userDao) {
        super(author, details);
        this.userDao = userDao;
        setType(CommandType.ALL_USERS);
    }

    @Override
    public Message process() {
        List<User> users = userDao.getUsers();
        if(users.isEmpty()){
            return new Message("No users in database. ", author);
        }else {
            StringBuilder builder = new StringBuilder("List of all users: \n");
            for (int i = 0; i < users.size(); i++) {
                buildIndexedLine(builder, i, users.get(i).getNickname()+", points: " + users.get(i).getPoints());
            }
            return new Message(builder.toString(), author);
        }
    }
}
