package com.paulocesar.entity;

import com.paulocesar.entity.enums.ClothingSize;
import com.paulocesar.entity.enums.ItemType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;

@Getter
@Setter
@ToString
@Entity(name = "tb_clothing")
@PrimaryKeyJoinColumn(name="id_item")
public class Clothing extends Item {

    private Character gender;

    @Enumerated(EnumType.STRING)
    private ClothingSize size;

    public Clothing(String itemName, Character gender, ClothingSize size) {
        super(ItemType.CLOTHES, itemName);
        this.gender = gender;
        this.size = size;
    }
}
