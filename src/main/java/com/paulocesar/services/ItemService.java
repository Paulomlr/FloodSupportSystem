package com.paulocesar.services;

import com.paulocesar.entity.Item;
import com.paulocesar.repositories.ItemRepository;

public class ItemService {
    private ItemRepository repository;

    public ItemService() {
        this.repository = new ItemRepository();
    }

    public void createItem(Item item){
        repository.save(item);
    }

    public Item getItem(Integer id){
        return repository.findById(id);
    }

    public void updateItem(Item item){
        repository.update(item);
    }

    public void deleteItem(Item item){
        repository.delete(item);
    }

    public void close(){
        repository.close();
    }
}
