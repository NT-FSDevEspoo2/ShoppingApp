/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opiframe.spring.boot.shoppingapp;

/**
 *
 * @author O
 */
public class TextResponse {

    public static String of(String message) {
        return "{\"message\": \"" + message + "\"}";
    }
}
