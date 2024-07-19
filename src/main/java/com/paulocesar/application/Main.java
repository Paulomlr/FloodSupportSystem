package com.paulocesar.application;

import com.paulocesar.entity.*;
import com.paulocesar.services.DistributionCenterService;
import com.paulocesar.services.ShelterService;
import com.paulocesar.util.*;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static final DistributionCenterService centerService = new DistributionCenterService();
    private static final ShelterService shelterService = new ShelterService();

    public static void loadCsvData(){
        DistributionCenterCSVReader centerCSVReader = new DistributionCenterCSVReader();
        DonationCSVReader donationCSVReader = new DonationCSVReader();
        ItemCSVReader itemCSVReader = new ItemCSVReader();
        DonationItemCSVReader donationItemCSVReader = new DonationItemCSVReader();
        ShelterCSVReader shelterCSVReader = new ShelterCSVReader();
        OrderCSVReader orderCSVReader = new OrderCSVReader();
        OrderItemCSVReader orderItemCSVReader = new OrderItemCSVReader();

        centerCSVReader.readerCSVFile();
        donationCSVReader.readerCSVFile();
        itemCSVReader.readerCSVFile();
        donationItemCSVReader.readerCSVFile();
        shelterCSVReader.readerCSVFile();
        orderCSVReader.readerCSVFile();
        orderItemCSVReader.readerCSVFile();
    }

    public static void main(String[] args) {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        logger.info("Starting the application...");

        loadCsvData();
        Scanner in = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            displayMenu();
            int option = in.nextInt();
            in.nextLine();

            switch (option){
                case 1 -> {
                    System.out.println();
                    listDistributionCenter(in);
                    System.out.println();
                }
                case 2 -> {
                    System.out.println();
                    listShelters();
                    System.out.println();
                }
                case 3 -> {
                    System.out.println();
                    createNewDistributionCenter(in);
                    System.out.println();
                }
                case 4 -> {
                    System.out.println();
                    createNewShelter(in);
                    System.out.println();
                }
                case 5 -> exit = true;
            }
        }

        centerService.close();
        shelterService.close();
        logger.info("Finishing the application...");
    }

    public static void displayMenu(){
        System.out.println("1. List Distribution Centers");
        System.out.println("2. List Shelters");
        System.out.println("3. Create New Distribution Center");
        System.out.println("4. Create New Shelter");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    private static void listDistributionCenter(Scanner in) {
        System.out.println("----------DISTRIBUTION CENTERS----------");
        List<DistributionCenter> centers = centerService.getAllDistributionCenter();

        for(DistributionCenter center : centers){
            System.out.println(center.getId() + ": " + center.getName());
            System.out.println("Stock: ");
            System.out.println("   Clothing stock: " + center.getClothingQuantity());
            System.out.println("   Food stock: " + center.getFoodQuantity());
            System.out.println("   Stock of hygiene products: " + center.getHygieneProductQuantity());
            System.out.println();
            System.out.println("Orders received: ");
            Set<Order> orders = center.getOrders();
            for(Order order : orders){
                System.out.println("   Shelter name: " + order.getShelter().getName() + " Status: " + order.getStatus());
            }
            System.out.println();
        }
    }

    private static void listShelters() {
        System.out.println("----------SHELTERS----------");
        List<Shelter> shelters = shelterService.getAllShelter();

        for(Shelter shelter: shelters){
            System.out.println(shelter.getId() + ": " + shelter.getName());
            System.out.println("Stock: ");
            System.out.println("  Clothing stock: " + shelter.getClothingQuantity());
            System.out.println("  Food stock: " + shelter.getFoodQuantity());
            System.out.println("  Stock of hygiene products: " + shelter.getHygieneProductQuantity());
        }
    }

    private static void createNewDistributionCenter(Scanner in) {
        System.out.println("----------CREATE DISTRIBUTION CENTER----------");
        System.out.print("Name: ");
        String name = in.nextLine();
        System.out.println("ADDRESS");
        System.out.print("Street: ");
        String street = in.nextLine();
        System.out.print("Number: ");
        String number = in.nextLine();
        System.out.print("District: ");
        String district = in.nextLine();
        System.out.print("City: ");
        String city = in.nextLine();
        System.out.print("State: ");
        String state = in.nextLine();
        System.out.print("Zip Code: ");
        String zipCode = in.nextLine();

        Address address = new Address(street, number, district, city, state, zipCode);
        DistributionCenter center = new DistributionCenter(name, address);
        centerService.createDistributionCenter(center);
        System.out.println("Distribution Center created!");
    }

    private static void createNewShelter(Scanner in) {
        System.out.println("----------CREATE SHELTER----------");
        System.out.print("Name: ");
        String name = in.nextLine();
        System.out.println("ADDRESS");
        System.out.print("Street: ");
        String street = in.nextLine();
        System.out.print("Number: ");
        String number = in.nextLine();
        System.out.print("District: ");
        String district = in.nextLine();
        System.out.print("City: ");
        String city = in.nextLine();
        System.out.println("State: ");
        String state = in.nextLine();
        System.out.print("Zip Code: ");
        String zipCode = in.nextLine();

        Address address = new Address(street, number, district, city, state, zipCode);

        System.out.print("Responsible: ");
        String responsible = in.nextLine();
        System.out.print("Phone: ");
        String phone = in.nextLine();
        System.out.print("Email: ");
        String email = in.nextLine();
        System.out.print("Capacity: ");
        Integer capacity = in.nextInt();
        System.out.print("Quantity People: ");
        Integer quantityPeople = in.nextInt();

        Shelter shelter = new Shelter(name, address, responsible, phone, email, capacity, quantityPeople);
        shelterService.createShelter(shelter);
        System.out.println("Shelter created!");
    }
}