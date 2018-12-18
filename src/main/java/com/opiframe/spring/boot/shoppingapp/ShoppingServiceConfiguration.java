/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opiframe.spring.boot.shoppingapp;

import com.opiframe.spring.boot.shoppingapp.items.service.ShoppingService;
import com.opiframe.spring.boot.shoppingapp.items.service.mongodb.ShoppingServiceMongoDbImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author O
 */
@Configuration
public class ShoppingServiceConfiguration {

    @Bean
    public ShoppingService shoppingService() {
        return new ShoppingServiceMongoDbImpl();
    }
}
