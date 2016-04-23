package com.aquariusmaster.phonebook.service;

import com.aquariusmaster.phonebook.dao.UserDao;
import com.aquariusmaster.phonebook.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by harkonnen on 20.04.16.
 */
@Service("userService")
public class JdbcUserService implements UserService {

    @Autowired
    UserDao userDao;


    public boolean save(User user) {
        return userDao.save(user);
    }

    public boolean exists(String username) {
        return userDao.exists(username);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
