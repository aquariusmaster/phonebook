package com.aquariusmaster.phonebook.dao;

import com.aquariusmaster.phonebook.entity.PhoneEntry;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by harkonnen on 18.04.16.
 */
@Component("phoneEntryDao")
public class JdbcPhoneEntryDao implements com.aquariusmaster.phonebook.dao.PhoneEntryDao {

    public List<PhoneEntry> getAllPhoneEntry() {
        List<PhoneEntry> phoneEntryList = new ArrayList<PhoneEntry>();
        return phoneEntryList;
    }

    public List<PhoneEntry> getPhoneEntryByUser(long userId) {
        List<PhoneEntry> phoneEntryList = new ArrayList<PhoneEntry>();
        return phoneEntryList;
    }
}
