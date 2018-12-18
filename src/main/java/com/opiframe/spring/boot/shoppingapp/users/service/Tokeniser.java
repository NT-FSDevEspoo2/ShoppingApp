/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opiframe.spring.boot.shoppingapp.users.service;

import java.security.SecureRandom;
import java.util.Base64;

/**
 *
 * @author O
 */
public class Tokeniser {

    public static String createToken() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[128];
        secureRandom.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        String token = encoder.encodeToString(bytes);

        return token;
    }
}
