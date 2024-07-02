package com.paulocesar.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistributionCenter {
    private static Integer MAX_CAPACITY = 1000;

    private Integer id;
    private String name;
    private String street;
    private String number;
    private String district;
    private String city;
    private String state;
    private String zipCode;

    public DistributionCenter(String name, String street, String number, String district, String city, String state, String zipCode) {
        this.name = name;
        this.street = street;
        this.number = number;
        this.district = district;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return name + ". "
                + street + ", "
                + number + " - "
                + district + ", "
                + city + " - "
                + state.toUpperCase() + ", "
                + zipCode;
    }
}
