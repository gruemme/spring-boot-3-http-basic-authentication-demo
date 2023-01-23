package com.example.authenticationdemo.model;

public class ToDoUser {

    private final String username;
    private final String passwordHash;

    public ToDoUser(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}
