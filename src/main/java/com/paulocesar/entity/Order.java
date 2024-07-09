package com.paulocesar.entity;

import com.paulocesar.entity.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Order {
    private Integer id;
    private Shelter shelter;
    private DistributionCenter distributionCenter;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private String reasonForRejection;

    @Setter(AccessLevel.NONE)
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order(Shelter shelter, DistributionCenter distributionCenter, LocalDateTime orderDate) {
        this.shelter = shelter;
        this.distributionCenter = distributionCenter;
        this.orderDate = orderDate;
        this.status = OrderStatus.PENDING;
    }
}
