package com.paulocesar;

import com.paulocesar.entity.*;
import com.paulocesar.services.DistributionCenterService;
import com.paulocesar.services.DonationService;
import com.paulocesar.services.ItemService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        DistributionCenterService centerService = new DistributionCenterService();
        ItemService itemService = new ItemService();
        DonationService donationService = new DonationService();

        Address address1 = new Address("Av. Boqueirão", "2450", "Igara", "Canoas", "RS", "92032-420");
        DistributionCenter dc1 = new DistributionCenter("Centro de Distribuição Esperança", address1);
        DistributionCenter dc2 = new DistributionCenter("Centro de Distribuição Prosperidade", address1);
        centerService.createDistributionCenter(dc1);
        centerService.createDistributionCenter(dc2);

        HygieneProduct soap = new HygieneProduct("Soap");
        HygieneProduct toothbrush = new HygieneProduct("Toothbrush");
        itemService.createItem(soap);
        itemService.createItem(toothbrush);

        Donation donation = new Donation(dc1, LocalDateTime.now());
        donationService.createDonation(donation);

        DonationItem item1 = new DonationItem(donation, soap, 100);
        DonationItem item2 = new DonationItem(donation, toothbrush, 100);
        donation.getItems().add(item1);
        donation.getItems().add(item2);
        donationService.updateDonation(donation);

        Donation findDonation = donationService.getDonation(donation.getId());
        System.out.println(findDonation);

        donationService.deleteDonationItem(donation, item2);

        findDonation = donationService.getDonation(donation.getId());
        System.out.println(findDonation);

        centerService.close();
        itemService.close();
        donationService.close();
    }
}