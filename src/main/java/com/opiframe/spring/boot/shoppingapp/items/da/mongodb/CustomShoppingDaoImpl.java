/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opiframe.spring.boot.shoppingapp.items.da.mongodb;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 *
 * @author O
 */
public class CustomShoppingDaoImpl implements CustomShoppingDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public boolean editItem(String id, ShoppingItemMongoDb shoppingItem) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        Update update = new Update();
        update.set("type", shoppingItem.getType());
        update.set("count", shoppingItem.getCount());
        update.set("price", shoppingItem.getPrice());

        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, ShoppingItemMongoDb.class);
        return updateResult.getModifiedCount() > 0;
    }

}
