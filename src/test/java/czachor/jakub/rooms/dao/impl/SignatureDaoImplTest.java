package czachor.jakub.rooms.dao.impl;

import czachor.jakub.rooms.config.HibernateConfig;
import czachor.jakub.rooms.dao.RoomDao;
import czachor.jakub.rooms.dao.SignatureDao;
import czachor.jakub.rooms.dao.UserDao;
import czachor.jakub.rooms.models.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfig.class)
@WebAppConfiguration
public class SignatureDaoImplTest {

    @Autowired
    private SignatureDao signatureDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoomDao roomDao;

    @Before
    @Transactional
    @Rollback()
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        signatureDao.deleteAll();
        userDao.deleteAll();
        roomDao.deleteAll();
    }

    @Test
    public void getSignaturesByUserNickname() {
        User user1 = UserTest.getSampleInstance();
        User user2 = UserTest.getSampleInstance();
        user2.setNickname("nickname2");
        userDao.addUser(user1);
        userDao.addUser(user2);
        insertSignature("content1", user1);
        insertSignature("content2", user1);
        insertSignature("content3", user2);

        List<Signature> signatures = signatureDao.getSignaturesByUserNickname("nickname");
        assertEquals(2, signatures.size());
        assertEquals("nickname", signatures.get(0).getUser().getNickname());
        assertEquals("nickname", signatures.get(1).getUser().getNickname());
    }

    private void insertSignature(String content, User user){
        Signature signature1 = SignatureTest.getSampleInstance();
        signature1.setContent(content);
        signature1.setUser(user);
        signatureDao.addSignature(signature1);
    }

    @Test
    public void getSignaturesByRoomKey() {
        Room room1 = RoomTest.getTestInstance();
        Room room2 = RoomTest.getTestInstance();
        room2.setKey("key2");
        roomDao.insertRoom(room1);
        roomDao.insertRoom(room2);
        insertSignature("content1", room1);
        insertSignature("content2", room1);
        insertSignature("content3", room2);

        List<Signature> signatures = signatureDao.getSignaturesByRoomKey("key");
        assertEquals(2, signatures.size());
        assertEquals("key", signatures.get(0).getRoom().getKey());
        assertEquals("key", signatures.get(1).getRoom().getKey());
    }

    private void insertSignature(String content, Room room){
        Signature signature1 = SignatureTest.getSampleInstance();
        signature1.setContent(content);
        signature1.setRoom(room);
        signatureDao.addSignature(signature1);
    }

    @Test
    public void addSignature() {
        Room room1 = RoomTest.getTestInstance();
        roomDao.insertRoom(room1);
        User user1 = UserTest.getSampleInstance();
        userDao.addUser(user1);
        Signature signature1 = SignatureTest.getSampleInstance();
        signature1.setRoom(room1);
        signature1.setUser(user1);
        signatureDao.addSignature(signature1);
        assertNotNull(signature1.getId());
    }
}