package com.example.foodorderingapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.foodorderingapp.model.FoodItem;
import com.example.foodorderingapp.model.ItemLookupRequest;
import com.example.foodorderingapp.repository.FoodItemRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "Application Status", description = "Provides the current status of the application.")
public class StatusController {

    // @Autowired
    // FoodItemSerice food;

      @Autowired
        private FoodItemRepository foodItemRepository;

    @Operation(summary = "Get Application Status", description = "Returns a simple message indicating that the application is running.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved status")
    @GetMapping("/status")
    public Map<String, String> getStatus() {

        List<FoodItem> food = foodItemRepository.findAll();

        System.out.println(food.toString());

        return Collections.singletonMap("status", "Application is up and running!");
    }

    @PostMapping("/search")
    public List<FoodItem> getItemsByIds(@RequestBody ItemLookupRequest request) {
        // Spring Data JPA provides findAllById(Iterable<ID> ids) out of the box


        return foodItemRepository.findByIdIn(request.getIds());
    }

}
