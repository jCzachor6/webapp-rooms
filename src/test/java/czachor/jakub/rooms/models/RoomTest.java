package czachor.jakub.rooms.models;

import static org.junit.Assert.*;

public class RoomTest {
    public static Room getTestInstance(){
        Room room = new Room();
        room.setName("name");
        room.setKey("key");
        room.setInfo("info");
        room.setId(1L);
        return room;
    }

}