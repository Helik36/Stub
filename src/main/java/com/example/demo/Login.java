package com.example.demo;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Login {
    private String login;
    private String password;
    private LocalDate currentDate = LocalDate.now();

    @Override
    public String toString() {
        return "{" +
                "\"login\": " + "\"" + login + "\"" +
                ", \"currentDate\": " + currentDate +
                '}';
    }


    public Login(String login, String password, LocalDate currentDate) {
        this.login = login;
        this.password = password;
        this.currentDate = currentDate;
    }

    public Login(String login,LocalDate currentDate) {
        this.login = login;
        this.currentDate = currentDate;
    }
    public Login() {
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

    public LocalDate getCurrentDate() {
        return currentDate;
    }


}

