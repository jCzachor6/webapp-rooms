package czachor.jakub.rooms.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ActiveUsersTest {
    private ActiveUsers activeUsers;

    @Before
    public void setUp() throws Exception {
        activeUsers = new ActiveUsers();
        activeUsers.putNewActiveUser("1", "user1");
        activeUsers.putNewActiveUser("2", "user2");
        activeUsers.putNewActiveUser("3", "user3");
    }

    @Test
    public void getUsernameBySessionIdTest() {
        String username = activeUsers.getUsernameBySessionId("2");
        assertEquals("user2", username);
    }

    @Test
    public void getSessionIdByUsernameTest() {
        String sessionId = activeUsers.getSessionIdByUsername("user2");
        assertEquals("2", sessionId);
    }

    @Test
    public void getUsernameBySessionIdNotFoundTest() {
        String username = activeUsers.getUsernameBySessionId("nonexisting");
        assertNull(username);
    }

    @Test
    public void getSessionIdByUsernameNotFoundTest() {
        String sessionId = activeUsers.getSessionIdByUsername("nonexisting");
        assertNull(sessionId);
    }

    @Test
    public void changeActiveUserUsernameTest() {
        activeUsers.changeActiveUserUsername("2", "newUsername");
        String username = activeUsers.getUsernameBySessionId("2");
        assertEquals("newUsername", username);
    }
}