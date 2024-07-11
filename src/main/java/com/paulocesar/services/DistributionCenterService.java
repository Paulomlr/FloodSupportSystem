package com.paulocesar.services;

import com.paulocesar.entity.DistributionCenter;
import com.paulocesar.repositories.DistributionCenterRepository;

import java.util.List;

public class DistributionCenterService {
    private final DistributionCenterRepository repository;

    public DistributionCenterService() {
        this.repository = new DistributionCenterRepository();
    }

    public void close(){
        repository.close();
    }

    public void createDistributionCenter(DistributionCenter distributionCenter){
        repository.save(distributionCenter);
    }

    public DistributionCenter getDistributionCenter(Integer id){
        return repository.findById(id);
    }

    public List<DistributionCenter> getAllDistributionCenter(){
        return repository.findAll();
    }

    public void updateDistributionCenter(DistributionCenter distributionCenter){
        repository.update(distributionCenter);
    }

    public void deleteDistributionCenter(DistributionCenter distributionCenter) {
        repository.delete(distributionCenter);
    }
}
