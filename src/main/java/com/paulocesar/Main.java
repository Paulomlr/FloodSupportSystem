package com.paulocesar;

import com.paulocesar.entity.DistributionCenter;
import com.paulocesar.entity.Shelter;
import com.paulocesar.services.DistributionCenterService;
import com.paulocesar.services.ShelterService;
import com.paulocesar.util.*;

import java.util.List;
import java.util.Scanner;
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

            switch (option){
                case 1 -> {
                    System.out.println();
                    listDistributionCenter();
                    System.out.println();
                }
                case 2 -> {
                    System.out.println();
                    listShelters();
                    System.out.println();
                }
                case 3 -> exit = true;
            }
        }
        centerService.close();
        logger.info("Finishing the application...");
    }

    public static void displayMenu(){
        System.out.println("1. List Distribution Centers");
        System.out.println("2. List Shelters");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
    }

    private static void listDistributionCenter() {
        System.out.println("----------DISTRIBUTION CENTERS----------");
        List<DistributionCenter> centers = centerService.getAllDistributionCenter();

        for(DistributionCenter center : centers){
            System.out.println(center.getId() + ": " + center.getName());
        }
    }

    private static void listShelters() {
        System.out.println("----------SHELTERS----------");
        List<Shelter> shelters = shelterService.getAllShelter();

        for(Shelter shelter: shelters){
            System.out.println(shelter.getId() + ": " + shelter.getName());
        }
    }
}