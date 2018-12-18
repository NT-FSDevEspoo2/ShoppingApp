/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opiframe.spring.boot.shoppingapp.items.service;

import java.util.List;

/**
 *
 * @author O
 */
public interface ShoppingService<K> {

    public List<ShoppingItemDto> getItems();

    public void addItem(ShoppingItemDto shoppingItem);

    public boolean editItem(K id, ShoppingItemDto shoppingItem);

    public List<ShoppingItemDto> searchItems(
            List<String> type,
            Integer minCount,
            Integer maxCount,
            Double minPrice,
            Double maxPrice
    );

    public boolean removeItem(K id);
}
