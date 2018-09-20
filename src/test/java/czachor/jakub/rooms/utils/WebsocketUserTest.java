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
import static org.mockito.Mockito.when;

public class WebsocketUserTest {

    @Mock
    private StatisticsDao statisticsDao;
    private WebsocketUser websocketUser;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        websocketUser = new WebsocketUser();
    }

    @Test
    public void generateTest() {
        Integer suffix = 0;
        Statistics statistics = new Statistics();
        statistics.setIntValue(suffix);
        when(statisticsDao.getTotalUsersJoinedStat()).thenReturn(statistics);
        websocketUser.generate(statisticsDao);
        assertTrue(websocketUser.isGenerated());
        String expected = Consts.NEW_USER_NAME + suffix;
        assertEquals(expected, websocketUser.getUsername());
    }

    @Test
    public void changeUsernameTest() {
        websocketUser.changeUsername("username");
        assertFalse(websocketUser.isGenerated());
        assertEquals("username", websocketUser.getUsername());
    }

    @Test(expected = UsernameNotAvailableException.class)
    public void changeUsernameFailTest() {
        String notAvailable = Consts.NEW_USER_NAME + "33";
        websocketUser.changeUsername(notAvailable);
    }
}