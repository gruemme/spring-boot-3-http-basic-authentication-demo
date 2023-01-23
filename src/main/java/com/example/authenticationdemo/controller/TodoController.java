package com.example.authenticationdemo.controller;

import com.example.authenticationdemo.dto.TodoInput;
import com.example.authenticationdemo.model.Todo;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {

    private final List<Todo> todos = new ArrayList<>();

    @GetMapping(path = "/todos")
    public List<Todo> getAllTodos() {
        return todos;
    }

    @GetMapping(path = "/me")
    @SecurityRequirement(name = "BasicAuth")
    public String whoAmI(Authentication authentication) {
        User authenticatedUser = (User) authentication.getPrincipal();
        return authenticatedUser.getUsername();
    }

    @PostMapping(path = "/todos")
    @SecurityRequirement(name = "BasicAuth")
    public Todo createTodo(Authentication authentication, @RequestBody TodoInput todoInput) {
        User authenticatedUser = (User) authentication.getPrincipal();
        System.out.println("Create Todo by " + authenticatedUser.getUsername());
        Todo newTodo = new Todo(todoInput.getName(), todoInput.getDue());
        todos.add(newTodo);

        return newTodo;
    }
}
