package com.paulocesar.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.paulocesar.entity.Address;
import com.paulocesar.entity.DistributionCenter;
import com.paulocesar.entity.Donation;
import com.paulocesar.services.DistributionCenterService;
import com.paulocesar.services.DonationService;
import lombok.Getter;
import lombok.Setter;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class DonationCSVReader {
    private static final String CSV_PATH = "src/main/resources/csv/donation.csv";

    DistributionCenterService centerService;
    DonationService donationService;

    public DonationCSVReader() {
        this.centerService = new DistributionCenterService();
        this.donationService = new DonationService();
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
                int centerId = Integer.parseInt(cols.get("center_id"));

                DistributionCenter center = centerService.getDistributionCenter(centerId);
                if(center != null){
                    Donation donation = new Donation(center, LocalDateTime.parse(cols.get("timestamp")));
                    donationService.createDonation(donation);
                }
                else {
                    System.err.println("Distribution Center with ID " + centerId + " not found.");
                }
            });
        }
        catch (IOException | CsvValidationException e){
            e.printStackTrace();
        }
        finally {
            centerService.close();
            donationService.close();
        }
    }
}
