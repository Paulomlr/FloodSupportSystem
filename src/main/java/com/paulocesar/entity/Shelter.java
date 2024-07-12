package com.paulocesar.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity(name = "tb_shelter")
public class Shelter {
    private static Integer MAX_CAPACITY_ITEM = 200;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address")
    private Address address;

    private String responsible;
    private String phone;
    private String email;
    private Integer capacity;
    private Double occupationPercentage;
    private Integer quantityPeople;

    private int foodQuantity = 0;
    private int clothingQuantity = 0;
    private int hygieneProductQuantity = 0;

    public Shelter(String name, Address address, String responsible, String phone, String email, Integer capacity) {
        this.name = name;
        this.address = address;
        this.responsible = responsible;
        this.phone = phone;
        this.email = email;
        this.capacity = capacity;
    }
}
