package com.paulocesar.repositories;

import com.paulocesar.entity.DistributionCenter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class DistributionCenterRepository {
    private final EntityManagerFactory emf;
    private final EntityManager em;

    public DistributionCenterRepository() {
        this.emf = Persistence.createEntityManagerFactory("FloodSupportSystemPU");
        this.em = emf.createEntityManager();
    }

    public void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    public void save(DistributionCenter distributionCenter){
        em.getTransaction().begin();
        em.persist(distributionCenter);
        em.getTransaction().commit();
    }

    public DistributionCenter findById(Integer id){
        return em.find(DistributionCenter.class, id);
    }

    public void update(DistributionCenter distributionCenter){
        em.getTransaction().begin();
        em.merge(distributionCenter);
        em.getTransaction().commit();
    }

    public void delete(DistributionCenter distributionCenter){
        em.getTransaction().begin();
        if(!em.contains(distributionCenter)){
            distributionCenter = em.merge(distributionCenter);
        }
        em.remove(distributionCenter);
        em.getTransaction().commit();
    }

    public List<DistributionCenter> findAll() {
        return em.createQuery("from tb_distribution_center", DistributionCenter.class).getResultList();
    }
}
