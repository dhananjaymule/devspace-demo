package com.example.foodorderingapp.repository;

import com.example.foodorderingapp.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {
    // Spring Data JPA provides standard CRUD methods automatically.
    // We can add custom query methods here if needed.
    List<FoodItem> findByIdIn(List<Long> ids);
}
