package com.example.authenticationdemo.service;

import com.example.authenticationdemo.model.ToDoUser;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final List<ToDoUser> toDoUsers;

    public UserDetailServiceImpl() {
        this.toDoUsers = List.of(new ToDoUser("alice", "$2a$12$OMI6sQE.Y/1fGbX5skZ0HuMHYAgvTgt.M1ccScB5BiB8NCIXxJs3i"),
                new ToDoUser("bob", "$2a$12$st/iOP9znQS2ueT9VSsIwOw5aoPLGVHkByHGUqz0jTvMWV2xQ2AI2"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Try to find user: " + username);
        Optional<ToDoUser> todoUser = toDoUsers.stream()
                .filter(u -> u.getUsername().equals(username))
                .findAny();

        if (todoUser.isEmpty()) {
            System.out.println("User not found");
            throw new UsernameNotFoundException(username);
        }
        System.out.println("User found");
        ToDoUser foundUser = todoUser.get();

        return new User(foundUser.getUsername(), foundUser.getPasswordHash(),
                Collections.singletonList(new SimpleGrantedAuthority("USER_ROLE")));
    }
}
