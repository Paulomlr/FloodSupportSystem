package com.paulocesar;

import com.paulocesar.util.*;

public class Main {
    public static void main(String[] args) {
        DistributionCenterCSVReader centerCSVReader = new DistributionCenterCSVReader();
        DonationCSVReader donationCSVReader = new DonationCSVReader();
        ItemCSVReader itemCSVReader = new ItemCSVReader();
        DonationItemCSVReader donationItemCSVReader = new DonationItemCSVReader();
        ShelterCSVReader shelterCSVReader = new ShelterCSVReader();

        centerCSVReader.readerCSVFile();
        donationCSVReader.readerCSVFile();
        itemCSVReader.readerCSVFile();
        donationItemCSVReader.readerCSVFile();
        shelterCSVReader.readerCSVFile();
    }
}