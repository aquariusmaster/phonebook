package com.aquariusmaster.phonebook.service;

import com.aquariusmaster.phonebook.dao.PhoneEntryDao;
import com.aquariusmaster.phonebook.entity.PhoneEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by harkonnen on 18.04.16.
 */
@Service("entryService")
public class JdbcPhoneEntryService implements PhoneEntryService {

    @Autowired
    PhoneEntryDao entryDao;

    public List<PhoneEntry> getAllPhoneEntry() {
        return entryDao.getAllPhoneEntry();
    }

    public List<PhoneEntry> getPhoneEntries(String username) {
        return entryDao.getPhoneEntries(username);
    }

    public boolean save(PhoneEntry phoneEntry) {
        return entryDao.save(phoneEntry);
    }

    public boolean update(PhoneEntry phoneEntry) {
        return entryDao.update(phoneEntry);
    }

    public boolean delete(long id) {
        return entryDao.delete(id);
    }

    public PhoneEntry getPhoneEntry(long id) {
        return entryDao.getPhoneEntry(id);
    }

    public List<PhoneEntry> searchPhoneEntry(String search,String username) {
        return entryDao.searchPhoneEntries(search, username);
    }
}
