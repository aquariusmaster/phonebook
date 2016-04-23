package com.aquariusmaster.phonebook.entity;

import com.sun.istack.internal.Nullable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by harkonnen on 18.04.16.
 */
public class PhoneEntry {

    private long id;
    @Size(min=4)
    private String secondName;
    @Size(min=4)
    private String firstName;
    @Size(min=4)
    private String patronymic;
    @Pattern(regexp = "^\\+380\\([0-9]{2}\\)[0-9]{7}")
    private String mobile;
    @Nullable
    private String tel;
    @Nullable
    private String address;
    @Pattern(regexp = "^(?:[a-zA-Z0-9_'^&/+-])+(?:\\.(?:[a-zA-Z0-9_'^&/+-])+)" +
            "*@(?:(?:\\[?(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))\\.)" +
            "{3}(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\]?)|(?:[a-zA-Z0-9-]+\\.)" +
            "+(?:[a-zA-Z]){2,}\\.?)$")
    private String email;
    private String username;

    public PhoneEntry(){
    }

    public PhoneEntry(String secondName, String firstName, String patronymic, String mobile, String tel, String address, String email, String username) {
        this.secondName = secondName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.mobile = mobile;
        this.tel = tel;
        this.address = address;
        this.email = email;
        this.username = username;
    }

    public PhoneEntry(long id, String secondName, String firstName, String patronymic, String mobile, String tel, String address, String email, String username) {
        this.id = id;
        this.secondName = secondName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.mobile = mobile;
        this.tel = tel;
        this.address = address;
        this.email = email;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneEntry that = (PhoneEntry) o;

        if (secondName != null ? !secondName.equals(that.secondName) : that.secondName != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (patronymic != null ? !patronymic.equals(that.patronymic) : that.patronymic != null) return false;
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        return !(username != null ? !username.equals(that.username) : that.username != null);

    }

    @Override
    public int hashCode() {
        int result = secondName != null ? secondName.hashCode() : 0;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PhoneEntry{" +
                "id=" + id + '\'' +
                "secondName='" + secondName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", mobile='" + mobile + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", username=" + username +
                '}';
    }
}
