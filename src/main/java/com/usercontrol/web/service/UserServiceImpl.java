package com.usercontrol.web.service;

import com.usercontrol.web.entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<User> getAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> rootEntry = cq.from(User.class);
        CriteriaQuery<User> all = cq.select(rootEntry);
        TypedQuery<User> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    @Override
    public User getById(long id) {
        return em.find(User.class, id);
    }

    @Override
    public void save(User user) {
        Session session = em.unwrap(Session.class);

        if (user.getId() == null) {
            session.save(user);
        } else {
            session.update(user);
        }

        session.close();
    }

    @Override
    public void removeById(long id) {
        em.remove(em.find(User.class, id));
        em.flush();
    }
}
