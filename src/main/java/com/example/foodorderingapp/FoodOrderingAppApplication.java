package com.example.foodorderingapp;

import com.example.foodorderingapp.model.FoodItem;
import com.example.foodorderingapp.repository.FoodItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;

@SpringBootApplication
public class FoodOrderingAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodOrderingAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(FoodItemRepository foodItemRepository) {
        return args -> {
            // This logic now only LOGS the data on startup.
            // It no longer stops to wait for user input.
            
            System.out.println("\n--- Food Ordering Backend Started ---");
            System.out.println("Checking database menu...");

            List<FoodItem> menu = foodItemRepository.findAll();
            if (menu.isEmpty()) {
                System.out.println("ALERT: Database is empty. Ensure data.sql is loaded.");
            } else {
                System.out.println("Menu loaded successfully. Available items: " + menu.size());
                menu.forEach(item -> System.out.println(" - " + item.getName() + " ($" + item.getPrice() + ")"));
            }
            System.out.println("-------------------------------------\n");
            
            // NO Scanner here! The application will stay running 
            // and wait for Vue.js to send HTTP requests.
        };
    }
}