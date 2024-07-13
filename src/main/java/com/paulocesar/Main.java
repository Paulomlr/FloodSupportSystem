package com.paulocesar;

import com.paulocesar.util.DistributionCenterCSVReader;
import com.paulocesar.util.DonationCSVReader;
import com.paulocesar.util.DonationItemCSVReader;
import com.paulocesar.util.ItemCSVReader;

public class Main {
    public static void main(String[] args) {
        DistributionCenterCSVReader centerCSVReader = new DistributionCenterCSVReader();
        DonationCSVReader donationCSVReader = new DonationCSVReader();
        ItemCSVReader itemCSVReader = new ItemCSVReader();
        DonationItemCSVReader donationItemCSVReader = new DonationItemCSVReader();

        centerCSVReader.readerCSVFile();
        donationCSVReader.readerCSVFile();
        itemCSVReader.readerCSVFile();
        donationItemCSVReader.readerCSVFile();
    }
}