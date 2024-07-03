package com.paulocesar.entity;

import com.paulocesar.entity.enums.ItemType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Item {
    private Integer id;
    private ItemType itemType;
    private String itemName;

    public Item(ItemType itemType, String itemName) {
        this.itemType = itemType;
        this.itemName = itemName;
    }
}
