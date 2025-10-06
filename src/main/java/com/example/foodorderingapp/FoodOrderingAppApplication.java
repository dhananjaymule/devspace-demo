package com.example.foodorderingapp;

import com.example.foodorderingapp.model.FoodItem;
import com.example.foodorderingapp.repository.FoodItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@SpringBootApplication
public class FoodOrderingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodOrderingAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(FoodItemRepository foodItemRepository) {
		return args -> {
			System.out.println("\n--- Welcome to the Food Ordering App ---");
			System.out.println("Fetching menu from the database...");

			// 1. Fetch and print all food items
			List<FoodItem> menu = foodItemRepository.findAll();
			if (menu.isEmpty()) {
				System.out.println("Could not find any food items in the database.");
				return;
			}

			System.out.println("\n--- MENU ---");
			menu.forEach(System.out::println);
			System.out.println("------------");

			// 2. Prompt user for input
			Scanner scanner = new Scanner(System.in);
			System.out.print("\nEnter the numbers of the items you want to order (space-separated): ");
			String input = scanner.nextLine();

			// 3. Parse input and find selected items
			List<Long> selectedIds;
			try {
				selectedIds = Arrays.stream(input.split("\\s+"))
						.map(Long::parseLong)
						.collect(Collectors.toList());
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter numbers only.");
				return;
			}

			List<FoodItem> selectedItems = foodItemRepository.findByIdIn(selectedIds);

			if (selectedItems.isEmpty()) {
				System.out.println("None of the selected items were found on the menu.");
				return;
			}
			
			System.out.println("\n--- YOUR ORDER ---");
			selectedItems.forEach(System.out::println);

			// 4. Calculate and print the total cost
			double totalAmount = selectedItems.stream()
					.mapToDouble(FoodItem::getPrice)
					.sum();

			System.out.println("--------------------");
			System.out.printf("Total Amount: $%.2f\n", totalAmount);
			System.out.println("Thank you for your order!\n");
		};
	}
}
