package com.example.foodorderingapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.foodorderingapp.model.FoodItem;
import com.example.foodorderingapp.repository.FoodItemRepository;

@Service
class FoodItemSerice{

        @Autowired
        private FoodItemRepository foodItemRepository;

        public void getAllFoodItems(){

                List<FoodItem> items = foodItemRepository.findAll();
                System.out.print("###############################");
                System.out.print(items);
        }

}