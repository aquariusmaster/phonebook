package com.aquariusmaster.phonebook.entity;

import junit.framework.TestCase;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by harkonnen on 18.04.16.
 */
public class PhoneEntryTest extends TestCase {

    @Test
    public void testPhoneEntry() {

        PhoneEntry ivanov = setupPhoneEntryIvanov();

        assertEquals("Expect first name Иван", "Иван", ivanov.getFirstName());
        assertEquals("Expect second name Иванов", "Иванов", ivanov.getSecondName());
        assertEquals("Expect Patronymic(отчество) Иванович", "Иванович", ivanov.getPatronymic());
        assertEquals("Expect mobile +380(66)1234567", "+380(66)1234567", ivanov.getMobile());
        assertEquals("Expect telephone 123456", "123456", ivanov.getTel());
        assertEquals("Expect address Kiev", "Kiev", ivanov.getAddress());
        assertEquals("Expect email mail@mail.ru", "mail@mail.ru", ivanov.getEmail());
        assertEquals("Expect username = I", "I", ivanov.getUsername());

        PhoneEntry petrov = new PhoneEntry();
        assertNotEquals(ivanov, petrov);
        petrov.setSecondName("Петров");
        petrov.setFirstName("Петр");
        petrov.setPatronymic("Петрович");
        petrov.setMobile("12345678");
        petrov.setTel("123456");
        petrov.setAddress("Kiev");
        petrov.setEmail("mail@mail.ru");

        assertNotEquals("Must not equals by full name", ivanov, petrov);

        petrov.setSecondName("Иванов");
        petrov.setFirstName("Иван");
        petrov.setPatronymic("Иванович");
        petrov.setMobile("+380(66)1234567");
        petrov.setTel("123456");
        petrov.setAddress("Kiev");
        petrov.setEmail("mail@mail.ru");
        petrov.setUsername("I");

        assertEquals("Must be equals", ivanov, petrov);

    }

    @Test
    public void testValidationPhoneEntry(){

        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();

        PhoneEntry ivanov = setupPhoneEntryIvanov();

        Set<ConstraintViolation<PhoneEntry>> violations = validator.validate(ivanov);
        assertTrue(violations.isEmpty());

        ivanov.setFirstName("Ива");
        ivanov.setEmail("mail@mailru");
        ivanov.setMobile("12");

        violations = validator.validate(ivanov);
        assertEquals(3, violations.size());

    }

    static PhoneEntry setupPhoneEntryIvanov(){
        PhoneEntry ivanov = new PhoneEntry();
        ivanov.setSecondName("Иванов");
        ivanov.setFirstName("Иван");
        ivanov.setPatronymic("Иванович");
        ivanov.setMobile("+380(66)1234567");
        ivanov.setTel("123456");
        ivanov.setAddress("Kiev");
        ivanov.setEmail("mail@mail.ru");
        ivanov.setUsername("I");

        return ivanov;
    }

}