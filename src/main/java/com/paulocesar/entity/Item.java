package com.paulocesar.entity;

import com.paulocesar.entity.enums.ItemType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "tb_item")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;
    private String itemName;

    public Item(ItemType itemType, String itemName) {
        this.itemType = itemType;
        this.itemName = itemName;
    }
}
