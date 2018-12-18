/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opiframe.spring.boot.shoppingapp.items.service.memory;

import org.springframework.stereotype.Service;

/**
 *
 * @author O
 */
@Service
public class ShoppingItemIdService {

    private int nextId = 100;

    public long getNextId() {
        return nextId++;
    }
}
