package com.paulocesar.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.paulocesar.entity.*;
import com.paulocesar.services.DistributionCenterService;
import com.paulocesar.services.DonationService;
import com.paulocesar.services.ItemService;
import lombok.Getter;
import lombok.Setter;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Getter
@Setter
public class DonationItemCSVReader {
    private static final String CSV_PATH = "src/main/resources/csv/donationItem.csv";

    DistributionCenterService centerService;
    DonationService donationService;
    ItemService itemService;

    public DonationItemCSVReader() {
        this.donationService = new DonationService();
        this.itemService = new ItemService();
        this.centerService = new DistributionCenterService();
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

            Set<Donation> set = new HashSet<>();

            lines.forEach(cols ->{
                int donationId = Integer.parseInt(cols.get("donation_id"));
                int itemId = Integer.parseInt(cols.get("item_id"));
                int quantity = Integer.parseInt(cols.get("quantity"));

                Optional<Donation> optionalDonation = set.stream()
                        .filter(d -> d.getId() == donationId)
                        .findFirst();

                Donation donation;
                if (optionalDonation.isPresent()) {
                    donation = optionalDonation.get();
                } else {
                    donation = donationService.getDonation(donationId);
                    if (donation == null) {
                        System.err.println("Donation with ID " + donationId + " not found.");
                    }
                    set.add(donation);
                }

                Item item = itemService.getItem(itemId);
                if(item == null){
                    System.err.println("Item with ID " + itemId + " not found.");
                }
                DonationItem donationItem = new DonationItem(donation, item, quantity);
                donation.getItems().add(donationItem);
            });
            set.forEach(donation -> {
                donationService.updateDonation(donation);
                centerService.processDonation(donation);
            });
        }
        catch (IOException | CsvValidationException e){
            e.printStackTrace();
        }
        finally {
            donationService.close();
            itemService.close();
            centerService.close();
        }
    }
}
