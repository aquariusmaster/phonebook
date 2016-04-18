package com.aquariusmaster.phonebook.entity;

import com.sun.istack.internal.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by harkonnen on 18.04.16.
 */
public class PhoneEntry {

    @Size(min=4)
    private String secondName;
    @Size(min=4)
    private String firstName;
    @Size(min=4)
    private String patronymic;
    @Size(min=12)
    @Pattern(regexp = "^\\+380\\([0-9]{2}\\)[0-9]{7}")
    private String mobile;
    @Nullable
    private String tel;
    @Nullable
    private String address;
    @Nullable
    @Pattern(regexp = "^(?:[a-zA-Z0-9_'^&/+-])+(?:\\.(?:[a-zA-Z0-9_'^&/+-])+)" +
            "*@(?:(?:\\[?(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))\\.)" +
            "{3}(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\]?)|(?:[a-zA-Z0-9-]+\\.)" +
            "+(?:[a-zA-Z]){2,}\\.?)$",
            message = "заданный имэйл не может существовать")
    private String email;
    private long userId;

    public PhoneEntry(){
    }

    public PhoneEntry(String secondName, String firstName, String patronymic, String mobile, String tel, String address, String email, long userId) {
        this.secondName = secondName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.mobile = mobile;
        this.tel = tel;
        this.address = address;
        this.email = email;
        this.userId = userId;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhoneEntry that = (PhoneEntry) o;

        if (userId != that.userId) return false;
        if (!secondName.equals(that.secondName)) return false;
        if (!firstName.equals(that.firstName)) return false;
        if (!patronymic.equals(that.patronymic)) return false;
        if (!mobile.equals(that.mobile)) return false;
        if (tel != null ? !tel.equals(that.tel) : that.tel != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        return !(email != null ? !email.equals(that.email) : that.email != null);

    }

    @Override
    public int hashCode() {
        int result = secondName.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + patronymic.hashCode();
        result = 31 * result + mobile.hashCode();
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "PhoneEntry{" +
                "secondName='" + secondName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", mobile='" + mobile + '\'' +
                ", tel='" + tel + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", userId=" + userId +
                '}';
    }
}
