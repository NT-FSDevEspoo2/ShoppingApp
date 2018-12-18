/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opiframe.spring.boot.shoppingapp.items.da.mongodb;

/**
 *
 * @author O
 */
public interface CustomShoppingDao {

    public boolean editItem(String id, ShoppingItemMongoDb shoppingItem);
}
