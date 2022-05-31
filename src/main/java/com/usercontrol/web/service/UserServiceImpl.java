package com.usercontrol.web.service;

import com.usercontrol.web.entity.User;
import com.usercontrol.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User getById(long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void save(User user) {
        repository.saveAndFlush(user);
    }

    @Override
    public void removeById(long id) {
        repository.deleteById(id);
        repository.flush();
    }
}
