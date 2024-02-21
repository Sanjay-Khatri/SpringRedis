package com.simple.spring.service;

import com.simple.spring.entity.User;

public interface UserService {

    void addUser(User user);

    User getUser(int userId);

    User deleteUser(int userId);

    void updateUser(User user);
}
