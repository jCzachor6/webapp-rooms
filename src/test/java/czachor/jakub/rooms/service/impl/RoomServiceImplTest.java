package czachor.jakub.rooms.service.impl;

import czachor.jakub.rooms.dao.RoomDao;
import czachor.jakub.rooms.exceptions.RoomDoesNotExistException;
import czachor.jakub.rooms.models.Room;
import czachor.jakub.rooms.models.RoomTest;
import czachor.jakub.rooms.models.asm.RoomAsm;
import czachor.jakub.rooms.models.dto.RoomDTO;
import czachor.jakub.rooms.service.RoomService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class RoomServiceImplTest {
    @Configuration
    static class RoomServiceTestContextConfiguration{
        @Bean
        public RoomService contactService(){
            return new RoomServiceImpl(roomDao(), roomAsm());
        }
        @Bean
        public RoomDao roomDao(){
            return Mockito.mock(RoomDao.class);
        }
        @Bean
        public RoomAsm roomAsm(){
            return new RoomAsm();
        }
    }

    @Autowired
    private RoomDao roomDao;

    @Autowired
    private RoomAsm roomAsm;

    @Autowired
    private RoomService roomService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getRoomByKeyTest() {
        Room room = RoomTest.getTestInstance();
        when(roomDao.getRoomByKey("key")).thenReturn(room);
        RoomDTO roomDTO = roomService.getRoomByKey("key");
        assertEquals(room.getKey(), roomDTO.getKey());
        assertEquals(room.getInfo(), roomDTO.getInfo());
        assertEquals(room.getName(), roomDTO.getName());
    }

    @Test(expected = RoomDoesNotExistException.class)
    public void getNonExistingRoomByKeyTest() {
        when(roomDao.getRoomByKey("key")).thenReturn(null);
        RoomDTO roomDTO = roomService.getRoomByKey("key");
    }
}