package com.aquariusmaster.phonebook.dao;

import com.aquariusmaster.phonebook.entity.User;

import java.util.List;

/**
 * Created by harkonnen on 19.04.16.
 */
public interface UserDao {

    boolean save(User user);
    boolean exists(String username);
    List<User> getAllUsers();
}
