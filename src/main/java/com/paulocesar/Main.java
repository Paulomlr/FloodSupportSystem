package com.paulocesar;

import com.paulocesar.entity.DistributionCenter;
import com.paulocesar.services.DistributionCenterService;
import com.paulocesar.util.DistributionCenterCSVReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DistributionCenterService centerService = new DistributionCenterService();

        DistributionCenterCSVReader readerCenter = new DistributionCenterCSVReader();
        readerCenter.readerCSVFile();

        List<DistributionCenter> centers = centerService.getAllDistributionCenter();
        centers.forEach(System.out::println);

        centerService.close();
    }
}