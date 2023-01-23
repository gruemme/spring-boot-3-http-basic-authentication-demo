package com.example.authenticationdemo.dto;

import java.time.LocalDate;

public class TodoInput {

    private final String name;
    private final LocalDate due;

    public TodoInput(String name, LocalDate due) {
        this.name = name;
        this.due = due;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDue() {
        return due;
    }
}
