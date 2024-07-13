package com.paulocesar.entity;

import com.paulocesar.entity.enums.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity(name = "tb_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_shelter")
    private Shelter shelter;

    @ManyToOne
    @JoinColumn(name = "id_distribution_center")
    private DistributionCenter distributionCenter;

    private String orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private String reasonForRejection;

    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "id.order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    public Order(Shelter shelter, DistributionCenter distributionCenter, String orderDate) {
        this.shelter = shelter;
        this.distributionCenter = distributionCenter;
        this.orderDate = orderDate;
        this.status = OrderStatus.PENDING;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
