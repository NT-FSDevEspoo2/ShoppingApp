/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opiframe.spring.boot.shoppingapp.items.service.mongodb;

import com.opiframe.spring.boot.shoppingapp.items.da.mongodb.ShoppingDaoMongoDb;
import com.opiframe.spring.boot.shoppingapp.items.da.mongodb.ShoppingItemMongoDb;
import com.opiframe.spring.boot.shoppingapp.items.service.ShoppingItemDto;
import com.opiframe.spring.boot.shoppingapp.items.service.ShoppingService;
import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author O
 */
public class ShoppingServiceMongoDbImpl implements ShoppingService<String> {

    @Autowired
    private ShoppingDaoMongoDb shoppingDao;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ShoppingItemDto> getItems() {
        Type type = new TypeToken<List<ShoppingItemDto>>() {
        }.getType();

        List<ShoppingItemMongoDb> items = shoppingDao.findAll();

        return modelMapper.map(items, type);
    }

    @Override
    public void addItem(ShoppingItemDto shoppingItem) {
        ShoppingItemMongoDb shoppingItemMongoDb = modelMapper.map(shoppingItem, ShoppingItemMongoDb.class);

        shoppingDao.insert(shoppingItemMongoDb);
    }

    @Override
    public boolean editItem(String id, ShoppingItemDto shoppingItem) {
        ShoppingItemMongoDb shoppingItemMongoDb = modelMapper.map(shoppingItem, ShoppingItemMongoDb.class);

        return shoppingDao.editItem(id, shoppingItemMongoDb);
    }

    @Override
    public List<ShoppingItemDto> searchItems(List<String> type, Integer minCount, Integer maxCount, Double minPrice, Double maxPrice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean removeItem(String id) {
        try {
            shoppingDao.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
