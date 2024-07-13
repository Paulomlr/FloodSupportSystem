package com.paulocesar.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.paulocesar.entity.Clothing;
import com.paulocesar.entity.Food;
import com.paulocesar.entity.HygieneProduct;
import com.paulocesar.entity.enums.ClothingSize;
import com.paulocesar.entity.enums.UnitMeasurement;
import com.paulocesar.services.ItemService;
import lombok.Getter;
import lombok.Setter;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.paulocesar.entity.enums.ItemType.*;

@Getter
@Setter
public class ItemCSVReader {
    private static final String CSV_PATH = "src/main/resources/csv/item.csv";

    ItemService service;

    public ItemCSVReader() {
        this.service = new ItemService();
    }

    public void readerCSVFile(){
        try {
            CSVReader reader = new CSVReader(new FileReader(CSV_PATH));

            List<Map<String, String>> lines = new ArrayList<>();

            String[] header = reader.readNext();
            String[] columns = null;

            while((columns = reader.readNext()) != null){
                Map<String, String> fields = new HashMap<>();

                for (int i = 0; i < columns.length; i++){
                    fields.put(header[i], columns[i]    );
                }
                lines.add(fields);
            }

            lines.forEach(cols -> {
                switch (cols.get("type")){
                    case "CLOTHES" -> {
                        String genderString = cols.get("gender");
                        char genderChar = genderString.charAt(0);

                        Clothing clothing = new Clothing(
                                cols.get("name"),
                                genderChar,
                                ClothingSize.valueOf(cols.get("size")));

                        service.createItem(clothing);
                    }
                    case "FOODS" -> {
                        Food food = new Food(cols.get("name"),
                                Double.parseDouble(cols.get("quantity")),
                                        UnitMeasurement.valueOf(cols.get("unitMeasurement")),
                                        LocalDate.parse(cols.get("expirationDate")));
                        service.createItem(food);
                    }
                    case "HYGIENE_PRODUCTS" -> {
                        HygieneProduct hygieneProduct = new HygieneProduct(cols.get("name"));
                        service.createItem(hygieneProduct);
                    }
                }
            });
            service.close();
        }
        catch (IOException | CsvValidationException e){
            e.printStackTrace();
        }
        finally {
            service.close();
        }
    }
}
