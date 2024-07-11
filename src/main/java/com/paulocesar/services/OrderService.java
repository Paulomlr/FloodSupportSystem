package com.paulocesar.services;

import com.paulocesar.entity.Order;
import com.paulocesar.entity.OrderItem;
import com.paulocesar.repositories.OrderRepository;

public class OrderService {
    private final OrderRepository repository;

    public OrderService(){
        this.repository = new OrderRepository();
    }

    public void createOrder(Order order){
        repository.save(order);
    }

    public Order getOrder(Integer id){
        return repository.findById(id);
    }

    public void updateOrder(Order order){
        repository.update(order);
    }

    public void deleteOrder(Order order){
        repository.delete(order);
    }

    public void deleteOrderItem(Order order, OrderItem item){
        repository.deleteOrderItem(order, item);
    }

    public void close(){
        repository.close();
    }
}
