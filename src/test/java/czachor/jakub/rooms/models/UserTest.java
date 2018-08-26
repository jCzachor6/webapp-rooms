package czachor.jakub.rooms.models;

import static org.junit.Assert.*;

public class UserTest {
    public static User getSampleInstance(){
        User user = new User();
        user.setNickname("nickname");
        user.setPoints(0);
        return user;
    }
}