package czachor.jakub.rooms.dao;

import czachor.jakub.rooms.models.Room;

public interface RoomDao {
    Room getRoomByKey(String key);
    void insertRoom(Room room);
    void deleteAll();
}
