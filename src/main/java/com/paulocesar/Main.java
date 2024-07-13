package com.paulocesar;

import com.paulocesar.services.ItemService;
import com.paulocesar.util.ItemCSVReader;

public class Main {
    public static void main(String[] args) {
        ItemService itemService = new ItemService();

        ItemCSVReader reader = new ItemCSVReader();
        reader.readerCSVFile();

        itemService.close();
    }
}