package czachor.jakub.rooms.dao.impl;

import czachor.jakub.rooms.dao.AbstractDao;
import czachor.jakub.rooms.dao.RoomDao;
import czachor.jakub.rooms.models.Room;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository("roomDao")
public class RoomDaoImpl extends AbstractDao<Long, Room> implements RoomDao {
    @Override
    public Room getRoomByKey(String key) {
        Query query =  createQuery("from Room where KEY =:key");
        query.setParameter("key", key);
        return (Room) query.uniqueResult();
    }

    @Override
    public void insertRoom(Room room) {
        persist(room);
    }
}
