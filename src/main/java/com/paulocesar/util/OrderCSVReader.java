package com.paulocesar.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.paulocesar.entity.DistributionCenter;
import com.paulocesar.entity.Donation;
import com.paulocesar.entity.Order;
import com.paulocesar.entity.Shelter;
import com.paulocesar.services.DistributionCenterService;
import com.paulocesar.services.DonationService;
import com.paulocesar.services.OrderService;
import com.paulocesar.services.ShelterService;
import lombok.Getter;
import lombok.Setter;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class OrderCSVReader {
    private static final String CSV_PATH = "src/main/resources/csv/order.csv";

    OrderService orderService;
    DistributionCenterService centerService;
    ShelterService shelterService;

    public OrderCSVReader() {
        this.orderService = new OrderService();
        this.centerService = new DistributionCenterService();
        this.shelterService = new ShelterService();
    }

    public void readerCSVFile(){
        try {
            CSVReader reader = new CSVReader(new FileReader(CSV_PATH));

            List<Map<String, String>> lines = new ArrayList<>();

            String[] header = reader.readNext();
            String[] colums = null;

            while((colums = reader.readNext()) != null){
                Map<String, String> fields = new HashMap<>();

                for (int i = 0; i < colums.length; i++){
                    fields.put(header[i], colums[i]);
                }
                lines.add(fields);
            }

            lines.forEach(cols ->{
                int shelterId = Integer.parseInt(cols.get("shelter_id"));
                int centerId = Integer.parseInt(cols.get("center_id"));

                Shelter shelter = shelterService.getShelter(shelterId);
                if(shelter == null){
                    System.err.println("Shelter with id " + shelterId + "not found");
                }

                DistributionCenter center = centerService.getDistributionCenter(centerId);
                if(center == null){
                    System.err.println("Distribution Center with ID " + centerId + " not found.");
                }

                ZoneId zoneId = ZoneId.of("America/Belem");
                LocalDateTime now = LocalDateTime.now(zoneId);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                String formattedDateTime = now.format(formatter);

                Order order = new Order(shelter, center, formattedDateTime);
                orderService.createOrder(order);
            });
        }
        catch (IOException | CsvValidationException e){
            e.printStackTrace();
        }
        finally {
            orderService.close();
            centerService.close();
            shelterService.close();
        }
    }
}
