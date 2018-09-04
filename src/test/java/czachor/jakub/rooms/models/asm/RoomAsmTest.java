package czachor.jakub.rooms.models.asm;

import czachor.jakub.rooms.models.Room;
import czachor.jakub.rooms.models.RoomTest;
import czachor.jakub.rooms.models.dto.RoomDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RoomAsmTest {
    private Room room;
    private RoomAsm roomAsm;

    @Before
    public void setUp() throws Exception {
        room = RoomTest.getTestInstance();
        roomAsm = new RoomAsm();
    }

    @Test
    public void toResource() {
        RoomDTO roomDTO = roomAsm.toResource(room);
        assertTrue(roomDTO.hasLinks());
        assertTrue(roomDTO.hasLink("self"));
        assertEquals("/room?key=key", roomDTO.getLink("self").getHref());
    }

    @Test
    public void map() {
        RoomDTO roomDTO = roomAsm.toResource(room);
        assertEquals(room.getKey(), roomDTO.getKey());
        assertEquals(room.getInfo(), roomDTO.getInfo());
        assertEquals(room.getName(), roomDTO.getName());
    }

    @Test
    public void map2() {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setName("name");
        roomDTO.setInfo("info");
        roomDTO.setKey("key");
        Room room = roomAsm.map(roomDTO);

        assertEquals(roomDTO.getKey(), room.getKey());
        assertEquals(roomDTO.getInfo(), room.getInfo());
        assertEquals(roomDTO.getName(), room.getName());
    }
}