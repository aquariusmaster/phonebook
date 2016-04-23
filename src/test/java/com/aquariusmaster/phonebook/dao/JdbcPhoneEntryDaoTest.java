package com.aquariusmaster.phonebook.dao;

import com.aquariusmaster.phonebook.entity.PhoneEntry;
import com.aquariusmaster.phonebook.entity.User;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
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
import java.util.List;

/**
 * Created by harkonnen on 18.04.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml", "classpath:database-context.xml"})
@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
public class JdbcPhoneEntryDaoTest extends TestCase {

    @Autowired
    PhoneEntryDao phoneEntryDao;
    @Autowired
    UserDao userDao;
    @Autowired
    private DataSource dataSource;

    @Before
    public void setUp() throws Exception {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        jdbcTemplate.execute("delete from entries");
        jdbcTemplate.execute("delete from users");

        PhoneEntry entry = getPhoneEntry();
        User user = JdbcUserDaoTest.setupUser();

        assertTrue(userDao.save(user));
        assertTrue(phoneEntryDao.save(entry));
    }


    @Test
    public void testGetAllEntries(){
        List<PhoneEntry> phoneEntries = phoneEntryDao.getAllPhoneEntry();
        assertNotNull("List of Phone Entries is null", phoneEntries);
        assertEquals("PhoneEntries size must be 0", 1, phoneEntries.size());


    }

    @Test
    public void testGetAllEntriesByUser(){
        List<PhoneEntry> phoneEntries = phoneEntryDao.getPhoneEntries("I");
        assertNotNull("List of Phone Entries is null", phoneEntries);
        assertEquals("PhoneEntries size must be 0", phoneEntries.size(), 0);
        phoneEntries = phoneEntryDao.getPhoneEntries("aquariusmaster");
        assertEquals("PhoneEntries size must be 0", 1, phoneEntries.size());
    }

    @Test
    public void testSaveEntry() {

        assertEquals(1, phoneEntryDao.getAllPhoneEntry().size());
        assertEquals(1, phoneEntryDao.getPhoneEntries("aquariusmaster").size());

        PhoneEntry entry = getPhoneEntry();
        User user = JdbcUserDaoTest.setupUser();
        entry.setUsername("testuser");
        user.setUsername("testuser");
        assertTrue(phoneEntryDao.save(entry));
        assertTrue(userDao.save(user));
        assertEquals(2, phoneEntryDao.getAllPhoneEntry().size());
        assertEquals(1, phoneEntryDao.getPhoneEntries("testuser").size());

    }

    @Test
    public void testGetUpdateEntry(){

        PhoneEntry retrivedEntry =  phoneEntryDao.getPhoneEntries("aquariusmaster").get(0);
        retrivedEntry.setEmail("another@mail.ua");
        retrivedEntry.setPatronymic("Иванович");
        retrivedEntry.setMobile("+380(73)1234567");
        assertTrue(phoneEntryDao.update(retrivedEntry));
        PhoneEntry updatedEntry =  phoneEntryDao.getPhoneEntry(retrivedEntry.getId());
        assertEquals("Иванович", updatedEntry.getPatronymic());
        assertEquals("+380(73)1234567", updatedEntry.getMobile());
        assertEquals("another@mail.ua", updatedEntry.getEmail());
    }

    @Test
    public void testDeleteEntry(){

        PhoneEntry retrivedEntry =  phoneEntryDao.getPhoneEntries("aquariusmaster").get(0);
        phoneEntryDao.delete(retrivedEntry.getId());
        assertEquals(0, phoneEntryDao.getAllPhoneEntry().size());
    }

    static PhoneEntry getPhoneEntry(){
        PhoneEntry entry = new PhoneEntry();
        entry.setSecondName("Алексеев");
        entry.setFirstName("Алексей");
        entry.setPatronymic("Алексеевич");
        entry.setMobile("+380(50)2046725");
        entry.setTel("123456");
        entry.setEmail("sadadaa");
        entry.setAddress("fdsf");
        entry.setUsername("aquariusmaster");

        return entry;
    }
}
