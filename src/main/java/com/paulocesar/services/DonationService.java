package com.paulocesar.services;

import com.paulocesar.entity.Donation;
import com.paulocesar.entity.DonationItem;
import com.paulocesar.repositories.DonationRepository;

import java.util.List;

public class DonationService {
    private DonationRepository repository;

    public DonationService() {
        this.repository = new DonationRepository();
    }

    public void close(){
        repository.close();
    }

    public void createDonation(Donation donation){
        repository.save(donation);
    }

    public Donation getDonation(Integer id){
        return repository.findById(id);
    }

    public List<Donation> getAllDonation(){
        return repository.findAll();
    }

    public void updateDonation(Donation donation){
        repository.update(donation);
    }

    public void deleteDonation(Donation donation){
        repository.delete(donation);
    }

    public void deleteDonationItem(Donation donation, DonationItem item){
        repository.deleteDonationItem(donation, item);
    }
}
