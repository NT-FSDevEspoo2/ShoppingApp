/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opiframe.spring.boot.shoppingapp.items.service.memory;

import com.opiframe.spring.boot.shoppingapp.items.da.memory.ShoppingItemMemory;
import com.opiframe.spring.boot.shoppingapp.items.service.ShoppingItemDto;
import com.opiframe.spring.boot.shoppingapp.items.service.ShoppingService;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author O
 */
@Service
public class ShoppingServiceMemoryImpl implements ShoppingService<Long> {

    private final List<ShoppingItemMemory> items = new ArrayList<>();
    @Autowired
    private ShoppingItemIdService shoppingItemIdService;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ShoppingItemDto> getItems() {
        Type type = new TypeToken<List<ShoppingItemDto>>() {
        }.getType();

        return modelMapper.map(items, type);
    }

    @Override
    public void addItem(ShoppingItemDto shoppingItem) {
        ShoppingItemMemory shoppingItemMemory = modelMapper.map(shoppingItem, ShoppingItemMemory.class);

        shoppingItemMemory.setId(shoppingItemIdService.getNextId());
        items.add(shoppingItemMemory);
    }

    @Override
    public boolean editItem(Long id, ShoppingItemDto shoppingItem) {
        ShoppingItemMemory shoppingItemMemory = modelMapper.map(shoppingItem, ShoppingItemMemory.class);

        for (int i = 0; i < items.size(); i++) {
            ShoppingItemMemory existingShoppingItem = items.get(i);
            if (Objects.equals(existingShoppingItem.getId(), id)) {
                items.set(i, shoppingItemMemory);
                return true;
            }
        }

        return false;
    }

    @Override
    public List<ShoppingItemDto> searchItems(
            List<String> type,
            Integer minCount,
            Integer maxCount,
            Double minPrice,
            Double maxPrice
    ) {
        List<ShoppingItemMemory> matchingItems = new ArrayList<>();

        for (ShoppingItemMemory item : items) {
            if (itemMatchesSearch(item, type, minCount, maxCount, minPrice, maxPrice)) {
                matchingItems.add(item);
            }
        }

        Type entityListType = new TypeToken<List<ShoppingItemDto>>() {
        }.getType();

        return modelMapper.map(matchingItems, entityListType);
    }

    private boolean itemMatchesSearch(
            ShoppingItemMemory item,
            List<String> typeCriterias,
            Integer minCount,
            Integer maxCount,
            Double minPrice,
            Double maxPrice
    ) {
        if (minCount != null && maxCount != null && minCount > maxCount) {
            throw new IllegalArgumentException("Min count cannot be higher than max count");
        }

        if (minPrice != null && maxPrice != null && minPrice > maxPrice) {
            throw new IllegalArgumentException("Min price cannot be higher than max price");
        }

        boolean typeCriteriaMatch = false;
        if (typeCriterias != null) {
            for (String typeCriteria : typeCriterias) {
                if (typeCriteria != null && item.getType().toLowerCase().contains(typeCriteria.toLowerCase())) {
                    typeCriteriaMatch = true;
                }
            }

            if (!typeCriteriaMatch) {
                return false;
            }
        }

        if (minCount != null && item.getCount() < minCount) {
            return false;
        }

        if (maxCount != null && item.getCount() > maxCount) {
            return false;
        }

        if (minPrice != null && item.getPrice() < minPrice) {
            return false;
        }

        if (maxPrice != null && item.getPrice() > maxPrice) {
            return false;
        }

        return true;
    }

    @Override
    public boolean removeItem(Long id) {
        for (ShoppingItemMemory item : items) {
            if (Objects.equals(item.getId(), id)) {
                items.remove(item);
                return true;
            }
        }

        return false;
    }

}
