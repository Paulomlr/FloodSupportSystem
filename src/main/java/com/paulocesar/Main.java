package com.paulocesar;

import com.paulocesar.entity.*;
import com.paulocesar.entity.enums.ClothingSize;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("FloodSupportSystemPU");
        EntityManager em = emf.createEntityManager();

        Address address1 = new Address("Av. Boqueirão", "2450", "Igara", "Canoas", "RS", "92032-420");

        DistributionCenter db1 = new DistributionCenter("Centro de Distribuição Esperança", address1);

        Shelter shelter = new Shelter("Abrigo Boa Esperança", address1, "João Silva", "555-1234", "joao@abrigo.com", 100);

        Item clothing = new Clothing("T-Shirt", 'M', ClothingSize.M);
        Item hygieneProduct = new HygieneProduct("Soap");
        Item hygieneProduct2 = new HygieneProduct("Pasta de dente");

        Donation donation = new Donation(db1, LocalDateTime.now());

        DonationItem donationItem = new DonationItem(donation, hygieneProduct, 10);
        DonationItem donationItem2 = new DonationItem(donation, hygieneProduct2, 10);

        donation.getItems().add(donationItem);
        donation.getItems().add(donationItem2);

        em.getTransaction().begin();
        em.persist(db1);
        em.persist(clothing);
        em.persist(hygieneProduct);
        em.persist(hygieneProduct2);
        em.persist(donation);
        em.persist(donationItem);
        em.persist(donationItem2);
        em.getTransaction().commit();

        db1.processDonation(donation);

        em.getTransaction().begin();
        em.merge(db1);
        em.getTransaction().commit();

        Item item1 = new HygieneProduct("Soap");
        Order order = new Order(shelter, db1, LocalDateTime.now());
        OrderItem orderItem = new OrderItem(order, item1, 19);
        order.getOrderItems().add(orderItem);

        em.getTransaction().begin();
        em.persist(shelter);
        em.persist(item1);
        em.persist(order);
        em.persist(orderItem);
        em.getTransaction().commit();

        db1.processOrder(order);

        em.getTransaction().begin();
        em.merge(db1);
        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}