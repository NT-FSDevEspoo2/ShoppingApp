/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opiframe.spring.boot.shoppingapp.items.web;

import com.opiframe.spring.boot.shoppingapp.TextResponse;
import com.opiframe.spring.boot.shoppingapp.items.service.ShoppingItemDto;
import com.opiframe.spring.boot.shoppingapp.items.service.ShoppingService;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author O
 */
@RestController
@RequestMapping("/api/shopping")
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

    @PostConstruct
    public void postConstruct() {
        System.out.println("ShoppingController is using: " + shoppingService.getClass());
    }

    @GetMapping
    public List<ShoppingItemDto> getItems(
            @RequestParam(value = "type", required = false) List<String> type,
            @RequestParam(value = "minCount", required = false) Integer minCount,
            @RequestParam(value = "maxCount", required = false) Integer maxCount,
            @RequestParam(value = "minPrice", required = false) Double minPrice,
            @RequestParam(value = "maxPrice", required = false) Double maxPrice
    ) {
        if (type == null && minCount == null && maxCount == null && minPrice == null && maxPrice == null) {
            return shoppingService.getItems();
        }

        return shoppingService.searchItems(type, minCount, maxCount, minPrice, maxPrice);
    }

    @PostMapping
    public String addItem(@RequestBody ShoppingItemDto item) {
        shoppingService.addItem(item);

        return TextResponse.of("successs");
    }

    @PostMapping("/{id}")
    public String editItem(@PathVariable("id") String id, @RequestBody ShoppingItemDto item) throws ItemNotFoundException {
        boolean edited = shoppingService.editItem(id, item);

        if (edited) {
            return TextResponse.of("success");
        } else {
            throw new ItemNotFoundException("Item not found");
        }
    }

    @DeleteMapping("/{id}")
    public String removeItem(@PathVariable("id") String id) throws ItemNotFoundException {
        boolean removed = shoppingService.removeItem(id);

        if (removed) {
            return TextResponse.of("success");
        } else {
            throw new ItemNotFoundException("Item not found");
        }
    }

}
