package com.aquariusmaster.phonebook.dao;

import com.aquariusmaster.phonebook.entity.User;
import junit.framework.TestCase;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;

import javax.sql.DataSource;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

/**
 * Created by harkonnen on 19.04.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml", "classpath:database-context.xml"})
@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
public class JdbcUserDaoTest extends TestCase {

    @Autowired
    UserDao userDao;
    @Autowired
    private DataSource dataSource;


    @Before
    public void setUp() throws Exception {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);

        jdbc.execute("delete from entries");
        jdbc.execute("delete from users");
    }

    @Test
    public void testSaveAndExistUser(){

        User user = setupUser();

        assertTrue(userDao.save(user));
        assertTrue(userDao.exists(user.getUsername()));

    }

    @Test
    public void testGetAllUsers(){
        User user = setupUser();
        assertTrue(userDao.save(user));
        assertEquals(1, userDao.getAllUsers().size());
        user.setUsername("testuser");
        assertTrue(userDao.save(user));
        assertEquals(2, userDao.getAllUsers().size());

    }

    static User setupUser(){
        User user = new User();
        user.setUsername("aquariusmaster");
        user.setPassword("passpass");
        user.setFullname("Бобров Андрей");
        user.setEmail("aquariumaster@yandex.ru");
        user.setAuthority("ADMIN");
        user.setEnabled(true);
        return user;
    }


}