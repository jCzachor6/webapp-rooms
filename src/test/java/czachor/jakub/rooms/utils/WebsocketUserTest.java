package czachor.jakub.rooms.utils;

import czachor.jakub.rooms.consts.Consts;
import czachor.jakub.rooms.dao.StatisticsDao;
import czachor.jakub.rooms.exceptions.UsernameNotAvailableException;
import czachor.jakub.rooms.models.Statistics;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WebsocketUserTest {
    @Mock
    private StatisticsDao statisticsDao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Statistics totalUsersJoined = mock(Statistics.class);
        when(totalUsersJoined.getIntValue()).thenReturn(0);
        when(statisticsDao.getTotalUsersJoinedStat()).thenReturn(totalUsersJoined);
    }

    @Test
    public void generateTest() {
        WebsocketUser websocketUser = new WebsocketUser();
        websocketUser.generate(statisticsDao);
        assertTrue(websocketUser.getUsername().contains(Consts.NEW_USER_NAME));
        assertTrue(websocketUser.getUsername().contains(Integer.toString(0)));
        assertTrue(websocketUser.isGenerated());
    }

    @Test
    public void changeUsernameValidateTest() {
        WebsocketUser websocketUser = new WebsocketUser();
        websocketUser.changeUsername("newUsername", true);
        assertFalse(websocketUser.isGenerated());
        assertEquals("newUsername", websocketUser.getUsername());
    }

    @Test(expected = UsernameNotAvailableException.class)
    public void changeUsernameValidateFailTest() {
        WebsocketUser websocketUser = new WebsocketUser();
        websocketUser.changeUsername(Consts.NEW_USER_NAME+"32", true);
    }
}