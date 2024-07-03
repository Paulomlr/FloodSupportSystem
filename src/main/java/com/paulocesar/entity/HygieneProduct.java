package com.paulocesar.entity;

import com.paulocesar.entity.enums.ItemType;

public class HygieneProduct extends Item{

    public HygieneProduct(String itemName) {
        super(ItemType.HYGIENE_PRODUCTS, itemName);
    }
}
