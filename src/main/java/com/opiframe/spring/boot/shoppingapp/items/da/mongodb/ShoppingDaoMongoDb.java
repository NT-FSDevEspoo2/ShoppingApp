/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opiframe.spring.boot.shoppingapp.items.da.mongodb;

import com.opiframe.spring.boot.shoppingapp.items.da.ShoppingDao;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author O
 */
@Repository
public interface ShoppingDaoMongoDb extends MongoRepository<ShoppingItemMongoDb, String>, CustomShoppingDao, ShoppingDao {

    public List<ShoppingItemMongoDb> findByTypeIgnoreCase(String type);

    @Query("{price:{$gte:?0,$lte:?1}}")
    public List<ShoppingItemMongoDb> findByPrice(double minPrice, double maxPrice);
}
