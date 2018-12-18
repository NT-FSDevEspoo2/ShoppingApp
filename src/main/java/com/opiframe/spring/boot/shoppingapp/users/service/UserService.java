/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opiframe.spring.boot.shoppingapp.users.service;

import com.opiframe.spring.boot.shoppingapp.users.service.errors.SessionNotFoundException;
import com.opiframe.spring.boot.shoppingapp.users.service.errors.WrongUsernameOrPasswordException;
import com.opiframe.spring.boot.shoppingapp.users.service.errors.UsernameAlreadyExistsException;
import com.opiframe.spring.boot.shoppingapp.users.da.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author O
 */
@Service
public class UserService {

    private final Map<String, User> loggedUsers = new HashMap<>();
    private final List<User> registeredUsers = new ArrayList<>();

    public boolean register(User user) throws UsernameAlreadyExistsException {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if (user.getPassword() == null || user.getPassword().length() < 4) {
            throw new IllegalArgumentException("Password length must be at least 4 characters");
        }

        for (User existingUser : registeredUsers) {
            if (existingUser.getUsername().equals(user.getUsername())) {
                throw new UsernameAlreadyExistsException("Username already exists");
            }
        }

        registeredUsers.add(user);
        System.out.println("User: " + user.getUsername() + " has registered");

        return true;
    }

    public String login(User user) throws WrongUsernameOrPasswordException {
        for (User existingUser : registeredUsers) {
            if (existingUser.getUsername().equals(user.getUsername())) {
                if (existingUser.getPassword().equals(user.getPassword())) {
                    String token = Tokeniser.createToken();
                    loggedUsers.put(token, user);
                    System.out.println("User: " + user.getUsername() + " has logged in");
                    return token;
                }
            }
        }

        throw new WrongUsernameOrPasswordException("Wrong username or pasword");
    }

    public void logout(String token) throws SessionNotFoundException {
        User loggedUser = loggedUsers.get(token);

        if (loggedUser != null) {
            loggedUsers.remove(token);
            System.out.println("User: " + loggedUser.getUsername() + " has logged out");
        } else {
            throw new SessionNotFoundException("Session for token not found");
        }
    }

    public boolean isUserLogged(String token) {
        return loggedUsers.containsKey(token);
    }
}
