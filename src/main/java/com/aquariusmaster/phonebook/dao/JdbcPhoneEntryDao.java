package com.aquariusmaster.phonebook.dao;

import com.aquariusmaster.phonebook.entity.PhoneEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.*;

/**
 * Created by harkonnen on 18.04.16.
 */
@Repository("phoneEntryDao")
public class JdbcPhoneEntryDao implements PhoneEntryDao {

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    public List<PhoneEntry> getAllPhoneEntry() {
        String SQL = "select * from entries, users where entries.username=users.username and users.enabled=true";
        return jdbcTemplate.query(SQL, new PhoneEntryMapper());
    }

    public List<PhoneEntry> getPhoneEntries(String username) {
        String SQL = "select * from entries, users where entries.username=users.username and users.enabled=true and entries.username=:username";
        return jdbcTemplate.query(SQL, new MapSqlParameterSource("username", username), new PhoneEntryMapper());
    }

    public PhoneEntry getPhoneEntry(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        String SQL = "select * from entries, users where entries.username=users.username and users.enabled=true and id=:id";
        return jdbcTemplate.queryForObject(SQL, params, new PhoneEntryMapper());
    }

    public List<PhoneEntry> searchPhoneEntries(String search, String username) {
        String SQL = "select * from (select * from entries, users where entries.username=users.username and users.enabled=true and entries.username=:username) where ";
        return jdbcTemplate.query(SQL, new MapSqlParameterSource("username", username), new PhoneEntryMapper());
    }

    @Transactional
    public boolean save(PhoneEntry phoneEntry) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(phoneEntry);
        String SQL = "insert into entries (secondName, firstName, patronymic, mobile, tel, address, email, username) values (:secondName, :firstName, :patronymic, :mobile, :tel, :address, :email, :username)";

        return jdbcTemplate.update(SQL, params) == 1;
    }

    public boolean update(PhoneEntry phoneEntry) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(phoneEntry);
        String SQL = "update entries set secondName=:secondName, firstName=:firstName, patronymic=:patronymic, mobile=:mobile," +
                " tel:=tel, address=:address, email=:email, username=username where id=:id";
        return jdbcTemplate.update(SQL, params) == 1;
    }

    public boolean delete(long id) {
        int countOfDeletedRows = jdbcTemplate.update("delete from entries where id=:id", new MapSqlParameterSource("id", id));
        System.out.println("Count " + countOfDeletedRows);
        return countOfDeletedRows == 1;
    }

}
