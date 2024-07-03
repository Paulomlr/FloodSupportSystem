package com.paulocesar.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private Integer id;
    private String street;
    private String number;
    private String district;
    private String city;
    private String state;
    private String zipCode;

    public Address(String street, String number, String district, String city, String state, String zipCode) {
        this.street = street;
        this.number = number;
        this.district = district;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return street + ", "
                + number + " - "
                + district + ", "
                + city + " - "
                + state.toUpperCase() + ", "
                + zipCode;
    }
}
