package com.usercontrol.web.dao;

import com.usercontrol.web.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> getAll() {
        return entityManager.createQuery("Select u from User u", User.class)
                .getResultList();
    }

    @Override
    public User getById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void removeById(long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }
}
