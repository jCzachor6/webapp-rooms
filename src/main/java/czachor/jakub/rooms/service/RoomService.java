package czachor.jakub.rooms.service;

import czachor.jakub.rooms.models.dto.RoomDTO;

public interface RoomService {
    RoomDTO getRoomByKey(String key);
}
