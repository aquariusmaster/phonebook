package com.aquariusmaster.phonebook.dao;

import com.aquariusmaster.phonebook.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by harkonnen on 19.04.16.
 */
@Repository("userDao")
public class JdbcUserDao implements UserDao{

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Transactional
    public boolean save(User user) {

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(user);

        return jdbcTemplate.update("insert into users (username, fullname, password, email, enabled, authority) values (:username, :fullname, :password, :email, :enabled, :authority)", params) == 1;
    }

    public boolean exists(String username) {
        return jdbcTemplate.queryForObject("select count(*) from users where username=:username",
                new MapSqlParameterSource("username", username), Integer.class) > 0;
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query("select * from users", BeanPropertyRowMapper.newInstance(User.class));
    }
}
