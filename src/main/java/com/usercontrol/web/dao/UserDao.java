package com.usercontrol.web.dao;

import com.usercontrol.web.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();

    User getById(long id);

    void removeById(long id);

    void save(User user);
}
