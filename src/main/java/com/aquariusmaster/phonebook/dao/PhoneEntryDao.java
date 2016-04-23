package com.aquariusmaster.phonebook.dao;

import com.aquariusmaster.phonebook.entity.PhoneEntry;

import java.util.List;

/**
 * Created by harkonnen on 18.04.16.
 */
public interface PhoneEntryDao {

    List<PhoneEntry> getAllPhoneEntry();
    List<PhoneEntry> getPhoneEntries(String username);
    boolean save(PhoneEntry phoneEntry);
    boolean update(PhoneEntry phoneEntry);
    boolean delete(long id);
    PhoneEntry getPhoneEntry(long id);
    List<PhoneEntry> searchPhoneEntries(String search, String username);
}
