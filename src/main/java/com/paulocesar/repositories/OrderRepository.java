package com.paulocesar.repositories;

import com.paulocesar.entity.Order;
import com.paulocesar.entity.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class OrderRepository {
    private final EntityManagerFactory emf;
    private final EntityManager em;

    public OrderRepository() {
        this.emf = Persistence.createEntityManagerFactory("FloodSupportSystemPU");
        this.em = emf.createEntityManager();
    }

    public void close() {
        em.close();
        emf.close();
    }

    public void save(Order order){
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
    }

    public Order findById(Integer id){
        return em.find(Order.class, id);
    }

    public void update(Order order){
        em.getTransaction().begin();
        em.merge(order);
        em.getTransaction().commit();
    }

    public void delete(Order order){
        em.getTransaction().begin();
        if(!em.contains(order)){
            order = em.merge(order);
        }
        em.remove(order);
        em.getTransaction().commit();
    }
    public void deleteOrderItem(Order order, OrderItem item){
        em.getTransaction().begin();
        order.getOrderItems().remove(item);
        em.remove(em.contains(item) ? item : em.merge(item));
        em.merge(order);
        em.getTransaction().commit();
    }
}
