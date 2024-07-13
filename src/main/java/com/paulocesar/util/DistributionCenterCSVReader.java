package com.paulocesar.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.paulocesar.entity.Address;
import com.paulocesar.entity.DistributionCenter;
import com.paulocesar.services.DistributionCenterService;
import lombok.Getter;
import lombok.Setter;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class DistributionCenterCSVReader {
    private static final String CSV_PATH = "src/main/resources/csv/distributionCenter.csv";

    DistributionCenterService service;

    public DistributionCenterCSVReader() {
        this.service = new DistributionCenterService();
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
                Address address = new Address(
                        cols.get("street"),
                        cols.get("number"),
                        cols.get("district"),
                        cols.get("city"),
                        cols.get("state"),
                        cols.get("zipCode"));
                DistributionCenter center = new DistributionCenter(cols.get("name"), address);
                service.createDistributionCenter(center);
            });
        }
        catch (IOException | CsvValidationException e){
            e.printStackTrace();
        }
        finally {
            service.close();
        }
    }
}
