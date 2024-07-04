package com.paulocesar;

import com.paulocesar.entity.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FloodSupportSystemPU");
        EntityManager em = emf.createEntityManager();

        Address address1 = new Address("Av. Boqueirão", "2450", "Igara", "Canoas", "RS", "92032-420");
        em.getTransaction().begin();
        em.persist(address1);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}