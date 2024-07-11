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

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "distributionCenter", cascade = CascadeType.ALL)
    private Set<Donation> donations = new HashSet<>();

    @OneToMany(mappedBy = "distributionCenter", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();

    @Setter(AccessLevel.NONE)
    private int foodQuantity = 0;

    @Setter(AccessLevel.NONE)
    private int clothingQuantity = 0;

    @Setter(AccessLevel.NONE)
    private int hygieneProductQuantity = 0;

    public DistributionCenter(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public void processDonation(Donation donation) {
        donations.add(donation);

        for (DonationItem donationItem : donation.getItems()) {
            switch (donationItem.getItem().getItemType()) {
                case CLOTHES -> clothingQuantity += donationItem.getQuantity();
                case FOODS -> foodQuantity += donationItem.getQuantity();
                case HYGIENE_PRODUCTS -> hygieneProductQuantity += donationItem.getQuantity();
            }
        }
    }

    public void processOrder(Order order) {
        for (OrderItem orderItem : order.getOrderItems()) {
            switch (orderItem.getItem().getItemType()) {
                case CLOTHES -> {
                    if (clothingQuantity < orderItem.getQuantity()) {
                        rejectOrder("Insufficient clothing stock");
                        return;
                    }
                }
                case FOODS -> {
                    if (foodQuantity < orderItem.getQuantity()) {
                        rejectOrder("Insufficient food stock");
                        return;
                    }
                }
                case HYGIENE_PRODUCTS -> {
                    if (hygieneProductQuantity < orderItem.getQuantity()) {
                        rejectOrder("Insufficient hygiene products stock");
                        return;
                    }
                }
            }
        }
        acceptOrder(order);
    }

    private void acceptOrder(Order order) {
        for (OrderItem orderItem : order.getOrderItems()) {
            switch (orderItem.getItem().getItemType()) {
                case CLOTHES -> clothingQuantity -= orderItem.getQuantity();
                case FOODS -> foodQuantity -= orderItem.getQuantity();
                case HYGIENE_PRODUCTS -> hygieneProductQuantity -= orderItem.getQuantity();
            }
        }
        order.getShelter().receiveItem(order.getOrderItems());
    }

    private void rejectOrder(String reason) {
        System.out.println("Order rejected: " + reason);
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