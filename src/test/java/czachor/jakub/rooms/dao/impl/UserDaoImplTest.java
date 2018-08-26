package czachor.jakub.rooms.dao.impl;

import czachor.jakub.rooms.config.HibernateConfig;
import czachor.jakub.rooms.dao.UserDao;
import czachor.jakub.rooms.models.User;
import czachor.jakub.rooms.models.UserTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfig.class)
@WebAppConfiguration
public class UserDaoImplTest {
    @Autowired
    private UserDao userDao;

    @Before
    @Transactional
    @Rollback()
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        userDao.deleteAll();
    }

    @Test
    public void findUserByNickname() {
        User user = UserTest.getSampleInstance();
        userDao.addUser(user);
        User user1 = userDao.findUserByNickname(user.getNickname());
        assertNotNull(user1);
        assertEquals(user1.getId(), user.getId());
    }

    @Test
    public void getUsers() {
        User user1 = UserTest.getSampleInstance();
        User user2 = UserTest.getSampleInstance();
        User user3 = UserTest.getSampleInstance();
        user1.setNickname("1");
        user2.setNickname("2");
        user3.setNickname("3");
        userDao.addUser(user1);
        userDao.addUser(user2);
        userDao.addUser(user3);
        List<User> users = userDao.getUsers();
        assertNotNull(users);
        assertEquals(3, users.size());
    }

    @Test
    public void getUsersWhenEmpty() {
        List<User> users = userDao.getUsers();
        assertNotNull(users);
        assertEquals(0, users.size());
    }
}