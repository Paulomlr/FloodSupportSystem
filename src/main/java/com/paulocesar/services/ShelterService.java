package com.paulocesar.services;

import com.paulocesar.entity.Shelter;
import com.paulocesar.repositories.ShelterRepository;

import java.util.List;

public class ShelterService {
    private final ShelterRepository repository;

    public ShelterService(){
        this.repository = new ShelterRepository();
    }

    public void createShelter(Shelter shelter){
        repository.save(shelter);
    }

    public Shelter getShelter(Integer id){
        return repository.findById(id);
    }

    public List<Shelter> getAllShelter(){
        return repository.findAll();
    }

    public void updateShelter(Shelter shelter){
        repository.update(shelter);
    }

    public void deleteShelter(Shelter shelter){
        repository.delete(shelter);
    }

    public void close(){
        repository.close();
    }
}
