package czachor.jakub.rooms.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

@Transactional
public abstract class AbstractDao<PK extends Serializable, T extends Serializable> {
    private final Class<T> persistentClass;

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public AbstractDao() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected T getByKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }

    protected void persist(T entity) {
        getSession().persist(entity);
    }

    protected Object merge(T entity) {
        return getSession().merge(entity);
    }

    protected void update(T entity) {
        getSession().update(entity);
    }

    protected void delete(T entity) {
        getSession().delete(entity);
    }

    protected Query createQuery(String hql){
        return getSession().createQuery(hql);
    }

    public void deleteAll(){
        createQuery("delete from "+persistentClass.getSimpleName()).executeUpdate();
    }
}