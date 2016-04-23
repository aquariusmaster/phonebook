package com.aquariusmaster.phonebook.entity;

import junit.framework.TestCase;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by harkonnen on 18.04.16.
 */
public class UserTest extends TestCase {

    @Test
    public void testUser(){

        User user = setupUser();

        assertEquals("Expect fullname Андрей", "Андрей", user.getFullname());
        assertEquals("usver", user.getUsername());
        assertTrue(user.isEnabled());
        assertEquals("USER", user.getAuthority());

        User anotherUser = new User();
        anotherUser.setUsername("usver");

        assertEquals(anotherUser, user);

    }

    @Test
    public void testValidUser(){

        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();

        User user = setupUser();

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.isEmpty());

        User notValidUser = setupUser();
        notValidUser.setUsername("Ёёёё");
        notValidUser.setPassword("1234");

        violations = validator.validate(notValidUser);
        System.out.println(violations);
        assertEquals(3, violations.size());



    }

    static User setupUser(){
        
        User user = new User();
        user.setUsername("usver");
        user.setPassword("passpass");
        user.setFullname("Андрей");
        user.setEmail("aquariusmaster@yandex.ru");
        user.setEnabled(true);
        user.setAuthority("USER");
        return user;
    }
}
