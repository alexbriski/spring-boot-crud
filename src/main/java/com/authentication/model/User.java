package com.authentication.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

//TODO: could reduce boiler plate with Lombok
//Should ideally create a DTO besides the entity, possibly using ModelMapper for conversion
//Using name as ID for simplicity, should be an Integer instead
@Entity
@Table
public class User {

    @Id
    private String name;
    private String password;
    private String emailAddress;
    private Date lastLoginDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name) && password.equals(user.password) &&
                emailAddress.equals(user.emailAddress) &&
                lastLoginDate.equals(user.lastLoginDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, emailAddress, lastLoginDate);
    }
}
