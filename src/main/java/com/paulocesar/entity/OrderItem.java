package com.paulocesar.entity;

import com.paulocesar.entity.pk.OrderItemPK;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import java.util.Objects;

@Getter
@Setter
public class OrderItem {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();
    private Integer quantity;

    public OrderItem(Order order, Item item, Integer quantity){
        id.setOrder(order);
        id.setItem(item);
        this.quantity = quantity;
    }

    public Order getOrder(){
        return id.getOrder();
    }

    public void seOrder(Order order){
        id.setOrder(order);
    }

    public Item getItem(){
        return id.getItem();
    }

    public void setItem(Item item){
        id.setItem(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
