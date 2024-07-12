package com.paulocesar.services;

import com.paulocesar.entity.OrderItem;
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

    public void receiveItem(Shelter shelter, List<OrderItem> orderItems){
        for(OrderItem orderItem : orderItems){
            switch (orderItem.getItem().getItemType()){
                case CLOTHES -> shelter.setClothingQuantity(shelter.getClothingQuantity() + orderItem.getQuantity());
                case HYGIENE_PRODUCTS -> shelter.setHygieneProductQuantity(shelter.getHygieneProductQuantity() + orderItem.getQuantity());
                case FOODS -> shelter.setFoodQuantity(shelter.getFoodQuantity() + orderItem.getQuantity());
            }
        }
        repository.update(shelter);
    }
}
