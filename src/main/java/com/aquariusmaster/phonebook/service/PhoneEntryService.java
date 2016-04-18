package com.aquariusmaster.phonebook.service;

import com.aquariusmaster.phonebook.entity.PhoneEntry;

import java.util.List;

/**
 * Created by harkonnen on 18.04.16.
 */
public interface PhoneEntryService {

    List<PhoneEntry> getAllPhoneEntry();
    List<PhoneEntry> getPhoneEntryByUser(long userId);
}
