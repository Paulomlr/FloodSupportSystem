package com.paulocesar.repositories;

import com.paulocesar.entity.Donation;
import com.paulocesar.entity.DonationItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DonationRepository {
    private final EntityManagerFactory emf;
    private final EntityManager em;

    public DonationRepository() {
        this.emf = Persistence.createEntityManagerFactory("FloodSupportSystemPU");
        this.em = emf.createEntityManager();
    }

    public void close() {
        em.close();
        emf.close();
    }

    public void save(Donation donation){
        em.getTransaction().begin();
        em.persist(donation);
        em.getTransaction().commit();
    }

    public Donation findById(Integer id){
        return em.find(Donation.class, id);
    }

    public List<Donation> findAll(){
        return em.createQuery("from tb_donation", Donation.class).getResultList();
    }

    public void update(Donation donation){
        em.getTransaction().begin();
        em.merge(donation);
        em.getTransaction().commit();
    }

    public void delete(Donation donation){
        em.getTransaction().begin();
        if (!em.contains(donation)) {
            donation = em.merge(donation);
        }
        em.remove(donation);
        em.getTransaction().commit();
    }

    public void deleteDonationItem(Donation donation, DonationItem item){
        em.getTransaction().begin();
        donation.getItems().remove(item);
        em.remove(em.contains(item) ? item : em.merge(item));
        em.merge(donation);
        em.getTransaction().commit();
    }
}
