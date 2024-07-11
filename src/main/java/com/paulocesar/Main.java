package com.paulocesar;

import com.paulocesar.entity.*;
import com.paulocesar.services.DistributionCenterService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        DistributionCenterService centerService = new DistributionCenterService();

        Address address1 = new Address("Av. Boqueirão", "2450", "Igara", "Canoas", "RS", "92032-420");
        DistributionCenter dc1 = new DistributionCenter("Centro de Distribuição Esperança", address1);
        DistributionCenter dc2 = new DistributionCenter("Centro de Distribuição Prosperidade", address1);
        centerService.createDistributionCenter(dc1);
        centerService.createDistributionCenter(dc2);

        DistributionCenter dc = centerService.getDistributionCenter(dc1.getId());
        System.out.println("Retrieved center: " + dc);

        List<DistributionCenter> centers = centerService.getAllDistributionCenter();
        System.out.println("All centers: " + centers);

        dc1.setName("Update Center Name");
        centerService.updateDistributionCenter(dc1);
        System.out.println(dc1);

        centerService.deleteDistributionCenter(dc1);

        centerService.close();
    }
}