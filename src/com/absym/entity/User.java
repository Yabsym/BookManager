package com.absym.entity;

public class User {
    private String account;
    private String password;
    private String username;
    private String type;

    public User(String account, String password, String username, String type) {
        this.account = account;
        this.password = password;
        this.username = username;
        this.type = type;
    }

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
