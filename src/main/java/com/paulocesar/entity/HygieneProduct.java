package com.paulocesar.entity;

import com.paulocesar.entity.enums.ItemType;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@NoArgsConstructor
@Entity(name = "tb_hygiene_product")
@PrimaryKeyJoinColumn(name="id_item")
public class HygieneProduct extends Item{

    public HygieneProduct(String itemName) {
        super(ItemType.HYGIENE_PRODUCTS, itemName);
    }

    @Override
    public String toString() {
        return "HygieneProduct{" +
                "itemName=" + getItemName() +
                '}';
    }
}
