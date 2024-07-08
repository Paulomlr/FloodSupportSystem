package com.paulocesar.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Shelter {
    private Integer id;
    private String name;
    private Address address;
    private String responsible;
    private String phone;
    private String email;
    private Integer capacity;
    private Double occupationPercentage;
    
    @Setter(AccessLevel.NONE)
    private List<Item> itemList;

    public Shelter(String name, Address address, String responsible, String phone, String email, Integer capacity) {
        this.name = name;
        this.address = address;
        this.responsible = responsible;
        this.phone = phone;
        this.email = email;
        this.capacity = capacity;
        this.itemList = new ArrayList<>();
    }
}
