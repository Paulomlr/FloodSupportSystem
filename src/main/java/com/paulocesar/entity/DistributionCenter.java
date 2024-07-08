package com.paulocesar.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity(name = "tb_distribution_center")
public class DistributionCenter {
    private static Integer MAX_CAPACITY = 1000;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address")
    private Address address;

    @ElementCollection
    @CollectionTable(name = "distribution_center_item", joinColumns = @JoinColumn(name = "id_distribution_center"))
    @MapKeyJoinColumn(name = "id_item")
    @Column(name = "quantity")
    private Map<Item, Integer> itemQuantities = new HashMap<>();

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "distributionCenter", cascade = CascadeType.ALL)
    private Set<Donation> donations = new HashSet<>();

    public DistributionCenter(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return name + ". "
                + address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DistributionCenter that = (DistributionCenter) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
