package com.usercontrol.web.service;

import com.usercontrol.web.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    User getById(long id);

    void save(User user);

    void removeById(long id);
}
