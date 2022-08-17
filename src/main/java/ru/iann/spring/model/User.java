package ru.iann.spring.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class User {
    private long id;

    @NotEmpty(message = "login couldn't be empty")
    private String login;

    @NotEmpty(message = "password couldn't be empty")
    @Size(min = 5, message = "length of the password should be more than 5")
    private String password;

    private Date date;

    public User() {
        this.date = new Date();
    }

    public User(long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.date = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
