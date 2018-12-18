/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opiframe.spring.boot.shoppingapp.users.service.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author O
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Wrong username or password")
public class WrongUsernameOrPasswordException extends Exception {

    public WrongUsernameOrPasswordException() {
    }

    public WrongUsernameOrPasswordException(String message) {
        super(message);
    }

    public WrongUsernameOrPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongUsernameOrPasswordException(Throwable cause) {
        super(cause);
    }

}
