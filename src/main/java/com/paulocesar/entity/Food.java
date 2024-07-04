package com.paulocesar.entity;

import com.paulocesar.entity.enums.ItemType;
import com.paulocesar.entity.enums.UnitMeasurement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity(name = "tb_food")
@PrimaryKeyJoinColumn(name="id_item")
public class Food extends Item {

    private Double quantity;

    @Enumerated(EnumType.STRING)
    private UnitMeasurement unitMeasurement;
    private LocalDate expirationDate;

    public Food(String itemName, Double quantity, UnitMeasurement unitMeasurement, LocalDate expirationDate) {
        super(ItemType.FOODS, itemName);
        this.quantity = quantity;
        this.unitMeasurement = unitMeasurement;
        this.expirationDate = expirationDate;
    }
}
