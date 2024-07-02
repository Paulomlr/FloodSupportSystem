package com.paulocesar.entity;

import com.paulocesar.entity.enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public abstract class Item {
    private ItemType itemType;
    private String itemName;
}
