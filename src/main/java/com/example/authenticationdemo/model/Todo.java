package com.example.authenticationdemo.model;

import java.time.Instant;
import java.time.LocalDate;

public class Todo {

    private final String name;
    private final Instant created = Instant.now();
    private final LocalDate due;

    public Todo(String name, LocalDate due) {
        this.name = name;
        this.due = due;
    }

    public String getName() {
        return name;
    }

    public Instant getCreated() {
        return created;
    }

    public LocalDate getDue() {
        return due;
    }
}
