package com.paulocesar.services;

import com.paulocesar.entity.*;
import com.paulocesar.entity.enums.OrderStatus;
import com.paulocesar.repositories.DistributionCenterRepository;

import java.util.List;

public class DistributionCenterService {
    private final DistributionCenterRepository repository;
    private final ShelterService shelterService;

    public DistributionCenterService() {
        this.repository = new DistributionCenterRepository();
        this.shelterService = new ShelterService();
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

    public void processDonation(Donation donation) {
        DistributionCenter center = donation.getDistributionCenter();
        center.getDonations().add(donation);

        for (DonationItem donationItem : donation.getItems()) {
            switch (donationItem.getItem().getItemType()) {
                case CLOTHES -> center.setClothingQuantity(center.getClothingQuantity() + donationItem.getQuantity());
                case FOODS -> center.setFoodQuantity(center.getFoodQuantity() + donationItem.getQuantity());
                case HYGIENE_PRODUCTS -> center.setHygieneProductQuantity(center.getHygieneProductQuantity() + donationItem.getQuantity()) ;
            }
        }
        repository.update(center);
    }

    public void processOrder(Order order) {
        DistributionCenter center = order.getDistributionCenter();

        for (OrderItem orderItem : order.getOrderItems()) {
            switch (orderItem.getItem().getItemType()) {
                case CLOTHES -> {
                    if (center.getClothingQuantity() < orderItem.getQuantity()) {
                        rejectOrder(order, "Insufficient clothing stock");
                        return;
                    }
                }
                case FOODS -> {
                    if (center.getFoodQuantity() < orderItem.getQuantity()) {
                        rejectOrder(order, "Insufficient food stock");
                        return;
                    }
                }
                case HYGIENE_PRODUCTS -> {
                    if (center.getHygieneProductQuantity() < orderItem.getQuantity()) {
                        rejectOrder(order, "Insufficient hygiene products stock");
                        return;
                    }
                }
            }
        }
        acceptOrder(order);
    }

    private void acceptOrder(Order order) {
        DistributionCenter center = order.getDistributionCenter();
        center.getOrders().add(order);

        for (OrderItem orderItem : order.getOrderItems()) {
            switch (orderItem.getItem().getItemType()) {
                case CLOTHES -> center.setClothingQuantity(center.getClothingQuantity() - orderItem.getQuantity());
                case FOODS -> center.setFoodQuantity(center.getFoodQuantity() - orderItem.getQuantity());
                case HYGIENE_PRODUCTS -> center.setHygieneProductQuantity(center.getHygieneProductQuantity() - orderItem.getQuantity()) ;
            }
        }
        order.setStatus(OrderStatus.ACCEPTED);
        Shelter shelter = order.getShelter();
        shelterService.receiveItem(shelter, order.getOrderItems());
        repository.update(center);
    }

    private void rejectOrder(Order order, String reason) {
        System.out.println("Order rejected: " + reason);
        order.setStatus(OrderStatus.REJECTED);
        order.setReasonForRejection(reason);
    }
}
