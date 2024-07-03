package com.paulocesar.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DistributionCenter {
    private static Integer MAX_CAPACITY = 1000;

    private Integer id;
    private String name;
    private Address address;

    public DistributionCenter(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return name + ". "
                + address;
    }
}
