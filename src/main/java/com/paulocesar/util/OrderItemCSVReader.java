package com.paulocesar.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.paulocesar.entity.*;
import com.paulocesar.services.*;
import lombok.Getter;
import lombok.Setter;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Getter
@Setter
public class OrderItemCSVReader {
    private static final String CSV_PATH = "src/main/resources/csv/orderItem.csv";

    OrderService orderService;
    ShelterService shelterService;
    ItemService itemService;
    DistributionCenterService centerService;

    public OrderItemCSVReader() {
        this.orderService = new OrderService();
        this.shelterService = new ShelterService();
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

            Set<Order> set = new HashSet<>();

            lines.forEach(cols ->{
                int orderId = Integer.parseInt(cols.get("order_id"));
                int itemId = Integer.parseInt(cols.get("item_id"));
                int quantity = Integer.parseInt(cols.get("quantity"));

                Optional<Order> optionalOrder = set.stream()
                        .filter(d -> d.getId() == orderId)
                        .findFirst();

                Order order;
                if (optionalOrder.isPresent()) {
                    order = optionalOrder.get();
                } else {
                    order = orderService.getOrder(orderId);
                    if (order == null) {
                        System.err.println("Order with ID " + orderId + " not found.");
                    }
                    set.add(order);
                }

                Item item = itemService.getItem(itemId);
                if(item == null){
                    System.err.println("Item with ID " + itemId + " not found.");
                }
                OrderItem orderItem = new OrderItem(order, item, quantity);
                order.getOrderItems().add(orderItem);
            });
            set.forEach(order -> {
                centerService.processOrder(order);
                orderService.updateOrder(order);
            });
        }
        catch (IOException | CsvValidationException e){
            e.printStackTrace();
        }
        finally {
            orderService.close();
            shelterService.close();
            itemService.close();
            centerService.close();
        }
    }
}
