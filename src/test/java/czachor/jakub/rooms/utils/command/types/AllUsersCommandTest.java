package czachor.jakub.rooms.utils.command.types;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.dao.UserDao;
import czachor.jakub.rooms.models.User;
import czachor.jakub.rooms.utils.message.Destination;
import czachor.jakub.rooms.utils.message.Message;
import czachor.jakub.rooms.utils.message.MessageProcessHelper;
import czachor.jakub.rooms.utils.message.MessageType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.Assert.*;

public class AllUsersCommandTest {

    private AllUsersCommand command;

    @Mock
    private UserDao userDao;

    @Mock
    private MessageProcessHelper helper;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        command = new AllUsersCommand(){
            @Override
            public UserDao getUserDao() {
                return userDao;
            }
        };
    }

    @Test
    public void processNoUsersTest() {
        when(userDao.getUsers()).thenReturn(Collections.emptyList());
        when(helper.getSessionId()).thenReturn("id");
        List<Message> messages = command.process(helper);
        assertEquals(1, messages.size());
        Message message = messages.get(0);
        assertEquals(Destination.Target.USER, message.getDestination().getTarget());
        assertEquals("id", message.getDestination().getTargetName());
        assertEquals(Consts.BOT_NAME , message.getFrom());
        assertEquals("No users in database. ", message.getLine());
        assertEquals(MessageType.COMMAND, message.getType());
    }

    @Test
    public void processWithExistingUsersTest() {
        User user1 = new User();
        user1.setNickname("user1");
        user1.setPoints(3);
        User user2 = new User();
        user2.setNickname("user2");
        user2.setPoints(5);
        when(userDao.getUsers()).thenReturn(Arrays.asList(user1, user2));
        when(helper.getSessionId()).thenReturn("id");
        List<Message> messages = command.process(helper);
        assertEquals(1, messages.size());
        Message message = messages.get(0);
        assertEquals(Destination.Target.USER, message.getDestination().getTarget());
        assertEquals("id", message.getDestination().getTargetName());
        assertEquals(Consts.BOT_NAME , message.getFrom());
        assertEquals("List of all users: \n" +
                "1. user1, points: 3\n" +
                "2. user2, points: 5\n", message.getLine());
        assertEquals(MessageType.COMMAND, message.getType());
    }
}