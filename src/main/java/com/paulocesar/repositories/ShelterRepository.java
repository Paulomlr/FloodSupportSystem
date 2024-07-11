package com.paulocesar.repositories;

import com.paulocesar.entity.Shelter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ShelterRepository {
    private final EntityManagerFactory emf;
    private final EntityManager em;

    public ShelterRepository() {
        this.emf = Persistence.createEntityManagerFactory("FloodSupportSystemPU");
        this.em = emf.createEntityManager();
    }

    public void close() {
        em.close();
        emf.close();
    }

    public void save(Shelter shelter){
        em.getTransaction().begin();
        em.persist(shelter);
        em.getTransaction().commit();
    }

    public Shelter findById(Integer id){
        return em.find(Shelter.class, id);
    }

    public List<Shelter> findAll(){
        return  em.createQuery("from tb_shelter", Shelter.class).getResultList();
    }

    public void update(Shelter shelter){
        em.getTransaction().begin();
        em.merge(shelter);
        em.getTransaction().commit();
    }

    public void delete(Shelter shelter){
        em.getTransaction().begin();
        if(!em.contains(shelter)){
            shelter = em.merge(shelter);
        }
        em.remove(shelter);
        em.getTransaction().commit();
    }
}
