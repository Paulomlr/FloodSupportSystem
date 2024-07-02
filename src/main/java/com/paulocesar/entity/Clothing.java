package com.paulocesar.entity;

import com.paulocesar.entity.enums.ItemType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Clothing extends Item {

    private Character gender;
    private String size;

    public Clothing(String itemName, Character gender, String size) {
        super(ItemType.CLOTHES, itemName);
        this.gender = gender;
        this.size = size;
    }
}
