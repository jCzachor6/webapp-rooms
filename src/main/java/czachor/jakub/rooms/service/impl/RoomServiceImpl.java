package czachor.jakub.rooms.service.impl;

import czachor.jakub.rooms.dao.RoomDao;
import czachor.jakub.rooms.exceptions.RoomDoesNotExistException;
import czachor.jakub.rooms.models.Room;
import czachor.jakub.rooms.models.asm.RoomAsm;
import czachor.jakub.rooms.models.dto.RoomDTO;
import czachor.jakub.rooms.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roomService")
public class RoomServiceImpl implements RoomService {
    private final RoomDao roomDao;

    private final RoomAsm roomAsm;

    @Autowired
    public RoomServiceImpl(RoomDao roomDao, RoomAsm roomAsm) {
        this.roomDao = roomDao;
        this.roomAsm = roomAsm;
    }

    @Override
    public RoomDTO getRoomByKey(String key) {
        Room found = roomDao.getRoomByKey(key);
        if(found != null){
            return roomAsm.toResource(found);
        }else {
            throw new RoomDoesNotExistException("Room with given key (" + key + ") doesn't exist");
        }
    }
}
