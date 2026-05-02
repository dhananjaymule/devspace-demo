package com.example.foodorderingapp.model;

import java.util.List;

public class ItemLookupRequest {
    private List<Long> ids;

    // Getters and Setters
    public List<Long> getIds() { return ids; }
    public void setIds(List<Long> ids) { this.ids = ids; }
} 