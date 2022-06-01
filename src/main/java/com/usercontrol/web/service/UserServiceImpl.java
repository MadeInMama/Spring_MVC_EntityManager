package com.usercontrol.web.service;

import com.usercontrol.web.entity.User;
import com.usercontrol.web.repository.UserRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    UserRepository repository;

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

        //return repository.findAll();
    }

    @Override
    public User getById(long id) {
        return em.find(User.class, id);

        //return repository.findById(id).orElse(null);
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

        //repository.saveAndFlush(user);
    }

    @Override
    public void removeById(long id) {
        em.remove(em.find(User.class, id));
        em.flush();
//        repository.deleteById(id);
//        repository.flush();
    }
}
