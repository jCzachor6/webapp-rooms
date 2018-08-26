package czachor.jakub.rooms.dao.impl;

import czachor.jakub.rooms.dao.AbstractDao;
import czachor.jakub.rooms.dao.UserDao;
import czachor.jakub.rooms.models.User;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Transactional
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

    @Override
    public void addUser(User user) {
        persist(user);
    }

    @Override
    public User findUserByNickname(String nickname) {
        Query query = createQuery("from User where nickname =:nickname");
        query.setParameter("nickname", nickname);
        return (User) query.uniqueResult();
    }

    @Override
    public List<User> getUsers() {
        Query query = createQuery("from User");
        List list = query.getResultList();
        if(list != null){
            return list;
        }else{
            return Collections.emptyList();
        }
    }
}
