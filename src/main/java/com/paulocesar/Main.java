package com.paulocesar;

import com.paulocesar.entity.*;
import com.paulocesar.entity.enums.ClothingSize;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FloodSupportSystemPU");
        EntityManager em = emf.createEntityManager();

        Address address1 = new Address("Av. Boqueirão", "2450", "Igara", "Canoas", "RS", "92032-420");
        DistributionCenter db1 = new DistributionCenter("Centro de Distribuição Esperança", address1);

        Item hygieneProduct = new HygieneProduct("Soap");
        Item clothing = new Clothing("T-Shirt", 'M', ClothingSize.M);

        em.getTransaction().begin();
        em.persist(db1);
        em.persist(clothing);
        em.persist(hygieneProduct);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}