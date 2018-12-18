/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opiframe.spring.boot.shoppingapp.users.web;

import com.opiframe.spring.boot.shoppingapp.TextResponse;
import com.opiframe.spring.boot.shoppingapp.users.da.User;
import com.opiframe.spring.boot.shoppingapp.users.service.UserService;
import com.opiframe.spring.boot.shoppingapp.users.service.errors.SessionNotFoundException;
import com.opiframe.spring.boot.shoppingapp.users.service.errors.UsernameAlreadyExistsException;
import com.opiframe.spring.boot.shoppingapp.users.service.errors.WrongUsernameOrPasswordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author O
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody User user) throws WrongUsernameOrPasswordException {
        String token = userService.login(user);

        return "{\"token\":\"" + token + "\"}";
    }

    @PostMapping("/logout")
    public String logout(@RequestHeader("token") String token) throws SessionNotFoundException {
        userService.logout(token);

        return TextResponse.of("success");
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) throws UsernameAlreadyExistsException {
        userService.register(user);

        return TextResponse.of("success");
    }
}
