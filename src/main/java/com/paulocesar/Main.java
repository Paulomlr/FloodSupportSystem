package com.paulocesar;

import com.paulocesar.util.DistributionCenterCSVReader;
import com.paulocesar.util.DonationCSVReader;

public class Main {
    public static void main(String[] args) {
        DistributionCenterCSVReader centerCSVReader = new DistributionCenterCSVReader();
        DonationCSVReader donationCSVReader = new DonationCSVReader();
        centerCSVReader.readerCSVFile();
        donationCSVReader.readerCSVFile();
    }
}