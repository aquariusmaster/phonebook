package com.aquariusmaster.phonebook.dao;

import com.aquariusmaster.phonebook.entity.PhoneEntry;
import com.aquariusmaster.phonebook.dao.JdbcPhoneEntryDao;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.ServletTestExecutionListener;

import java.util.List;

/**
 * Created by harkonnen on 18.04.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
@TestExecutionListeners(listeners={ServletTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
public class JdbcPhoneEntryDaoTest extends TestCase {

    @Autowired
    JdbcPhoneEntryDao phoneEntryDao;

    @Test
    public void testGetAllBooks(){
        List<PhoneEntry> phoneEntries = phoneEntryDao.getAllPhoneEntry();
        assertNotNull("List of Phone Entries is null", phoneEntries);
        assertEquals("PhoneEntries size must be 0", phoneEntries.size(), 0);
    }

    @Test
    public void testGetAllBooksByUser(){
        List<PhoneEntry> phoneEntries = phoneEntryDao.getAllPhoneEntry();
        assertNotNull("List of Phone Entries is null", phoneEntries);
        assertEquals("PhoneEntries size must be 0", phoneEntries.size(), 0);
    }
}
