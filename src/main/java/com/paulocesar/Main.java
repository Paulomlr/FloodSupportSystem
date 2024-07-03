package com.paulocesar;

import com.paulocesar.entity.*;
import com.paulocesar.entity.enums.ClothingSize;
import com.paulocesar.entity.enums.UnitMeasurement;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Item i1 = new Food("Arroz", 5.0, UnitMeasurement.KG, LocalDate.now());
        System.out.println(i1);

        Item i2 = new Clothing("Camisa", 'M', ClothingSize.M);
        System.out.println(i2);

        Item i3 = new HygieneProduct("Pasta de dente");
        System.out.println(i3);
    }
}