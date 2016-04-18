package com.aquariusmaster.phonebook.entity;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by harkonnen on 18.04.16.
 */
public class User {

    @Size(min=5)
    @Pattern(regexp = "[a-z]{3,}")
    private String username;
    @Size(min=5)
    private String password;
    @Size(min=5)
    private String fullname;
    private boolean enabled = false;
    private String authority;

    public User(){}

    public User(String username, String password, String fullname, boolean enabled, String authority) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.enabled = enabled;
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return username.equals(user.username);

    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullname='" + fullname + '\'' +
                ", enabled=" + enabled +
                ", authority='" + authority + '\'' +
                '}';
    }
}
