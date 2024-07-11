package com.paulocesar.repositories;

import com.paulocesar.entity.Item;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ItemRepository {
    private final EntityManagerFactory emf;
    private final EntityManager em;

    public ItemRepository() {
        this.emf = Persistence.createEntityManagerFactory("FloodSupportSystemPU");
        this.em = emf.createEntityManager();
    }

    public void close() {
        em.close();
        emf.close();
    }

    public void save(Item item){
        em.getTransaction().begin();
        em.persist(item);
        em.getTransaction().commit();
    }

    public Item findById(Integer id){
        return em.find(Item.class, id);
    }

    public void update(Item item){
        em.getTransaction().begin();
        em.merge(item);
        em.getTransaction().commit();
    }

    public void delete(Item item){
        em.getTransaction().begin();
        if (!em.contains(item)) {
            item = em.merge(item);
        }
        em.remove(item);
        em.getTransaction().commit();
    }
}
