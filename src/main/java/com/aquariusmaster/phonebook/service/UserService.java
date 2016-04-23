package com.aquariusmaster.phonebook.service;

import com.aquariusmaster.phonebook.entity.User;

import java.util.List;

/**
 * Created by harkonnen on 20.04.16.
 */
public interface UserService {

    boolean save(User user);
    boolean exists(String username);
    List<User> getAllUsers();
}
