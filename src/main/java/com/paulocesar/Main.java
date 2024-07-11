package com.paulocesar;

import com.paulocesar.entity.*;
import com.paulocesar.services.*;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        DistributionCenterService centerService = new DistributionCenterService();
        ItemService itemService = new ItemService();
        DonationService donationService = new DonationService();
        OrderService orderService = new OrderService();
        ShelterService shelterService = new ShelterService();

        Address address1 = new Address("Av. Boqueirão", "2450", "Igara", "Canoas", "RS", "92032-420");
        Address address2 = new Address("Av. Borges de Medeiros,", "1501", "Cidade Baixa", "Porto Alegre", "RS", "90119-900");
        Address address3 = new Address("RS-118", "2591", "Tarumã", "Viamão", "RS", "94425-500");

        DistributionCenter dc1 = new DistributionCenter("Centro de Distribuição Esperança", address1);
        DistributionCenter dc2 = new DistributionCenter("Centro de Distribuição Prosperidade", address2);
        centerService.createDistributionCenter(dc1);
        centerService.createDistributionCenter(dc2);

        Shelter shelter = new Shelter("Abrigo João Paulo II", address3, "Carlos", "99999-9999",
                "carlos@gmail.com", 100);
        shelterService.createShelter(shelter);

        HygieneProduct soap = new HygieneProduct("Soap");
        HygieneProduct toothbrush = new HygieneProduct("Toothbrush");
        itemService.createItem(soap);
        itemService.createItem(toothbrush);

        Order order = new Order(shelter, dc1, LocalDateTime.now());
        orderService.createOrder(order);

        OrderItem orderItem1 = new OrderItem(order, soap, 100);
        order.getOrderItems().add(orderItem1);
        orderService.updateOrder(order);

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

        Shelter findShelter = shelterService.getShelter(shelter.getId());
        System.out.println(findShelter);

        Order findOrder = orderService.getOrder(order.getId());
        System.out.println(findOrder);

        centerService.close();
        itemService.close();
        donationService.close();
        orderService.close();
        shelterService.close();
    }
}