package com.example.demo;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private String login;
    private String password;
    private Date currentDate;
    private String email;

    @Override
    public String toString() {
        return "{" +
                "\"login\": " + "\"" + login + "\"" +
                "\"password\": " + "\"" + password + "\"" +
                "\"email\": " + "\"" + email + "\"" +
                ", \"currentDate\": " + currentDate +
                '}';
    }

    public User(String login, String password, Date currentDate) {
        this.login = login;
        this.password = password;
        this.currentDate = currentDate;

    }

    public User(String login, Date currentDate) {
        this.login = login;
        this.currentDate = currentDate;
    }
    public User() {
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

    public Date getCurrentDate() {
        return currentDate;
    }
    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

