package com.paulocesar;

import com.paulocesar.entity.*;
import com.paulocesar.entity.enums.ClothingSize;
import com.paulocesar.entity.enums.UnitMeasurement;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Address address1 = new Address("Av. Boqueirão", "2450", "Igara", "Canoas", "RS", "92032-420");
        Address address2 = new Address("RS-118", "2591", "Tarumã", "Viamão", "RS", "94425-500");

        DistributionCenter db1 = new DistributionCenter("Centro de Distribuição Esperança", address1);

        Item item1 = new Clothing("Agasalho", 'M', ClothingSize.M);
        item1.setId(2);
        Item item3 = new Food("Arroz", 5.0, UnitMeasurement.KG, LocalDate.now());

        Shelter shelter1 = new Shelter("Abrigo João Paulo II", address2, "Carlos", "99999-9999",
                "carlos@gmail.com", 100);

        Donation d1 = new Donation(db1, LocalDateTime.now());
        d1.setId(1);
        DonationItem dItem1 = new DonationItem(d1, item1, 10);
        System.out.println(dItem1);


    }
}