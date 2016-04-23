package com.aquariusmaster.phonebook.dao;

import com.aquariusmaster.phonebook.entity.PhoneEntry;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by harkonnen on 19.04.16.
 */
public class PhoneEntryMapper implements RowMapper<PhoneEntry> {
    public PhoneEntry mapRow(ResultSet rs, int i) throws SQLException {
        PhoneEntry entry = new PhoneEntry();

        entry.setId(rs.getLong("id"));
        entry.setFirstName(rs.getString("firstName"));
        entry.setSecondName(rs.getString("secondName"));
        entry.setPatronymic(rs.getString("patronymic"));
        entry.setMobile(rs.getString("mobile"));
        entry.setTel(rs.getString("tel"));
        entry.setAddress(rs.getString("address"));
        entry.setEmail(rs.getString("email"));
        entry.setUsername(rs.getString("username"));

        return entry;
    }
}
