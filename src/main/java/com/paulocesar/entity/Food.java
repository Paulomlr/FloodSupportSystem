package com.paulocesar.entity;

import com.paulocesar.entity.enums.ItemType;
import com.paulocesar.entity.enums.UnitMeasurement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class Food extends Item {

    private Double quantity;
    private UnitMeasurement unitMeasurement;
    private LocalDate expirationDate;

    public Food(String itemName, Double quantity, UnitMeasurement unitMeasurement, LocalDate expirationDate) {
        super(ItemType.FOODS, itemName);
        this.quantity = quantity;
        this.unitMeasurement = unitMeasurement;
        this.expirationDate = expirationDate;
    }
}
