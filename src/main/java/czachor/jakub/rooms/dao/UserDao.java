package czachor.jakub.rooms.dao;

import czachor.jakub.rooms.models.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);
    User findUserByNickname(String nickname);
    List<User> getUsers();
    void deleteAll();
}
