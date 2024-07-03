package com.paulocesar.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Map<Class<? extends Item>, List<Item>> itemList;

    public Shelter(String name, Address address, String responsible, String phone, String email, Integer capacity) {
        this.name = name;
        this.address = address;
        this.responsible = responsible;
        this.phone = phone;
        this.email = email;
        this.capacity = capacity;
        this.itemList = new HashMap<>();
    }

    public void addItem(Item item) {
        Class<? extends Item> itemType = item.getClass();
        itemList.computeIfAbsent(itemType, k -> new ArrayList<>()).add(item);
    }
}
