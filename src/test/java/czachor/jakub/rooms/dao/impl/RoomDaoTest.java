package czachor.jakub.rooms.dao.impl;

import czachor.jakub.rooms.config.HibernateConfig;
import czachor.jakub.rooms.dao.RoomDao;
import czachor.jakub.rooms.models.Room;
import czachor.jakub.rooms.models.RoomTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfig.class)
@WebAppConfiguration
public class RoomDaoTest {

    @Autowired
    private RoomDao roomDao;

    @Before
    @Transactional
    @Rollback(false)
    public void setUp() throws Exception {
    }

    @Test
    public void getRoomByKey() {
        roomDao.insertRoom(RoomTest.getTestInstance());
        Room room = roomDao.getRoomByKey("key");
        assertEquals("info", room.getInfo());
        assertEquals("name", room.getName());
        assertEquals("key", room.getKey());
        assertEquals(new Long(1), room.getId());
    }
}