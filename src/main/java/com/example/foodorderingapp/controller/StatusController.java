package com.example.foodorderingapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@Tag(name = "Application Status", description = "Provides the current status of the application.")
public class StatusController {

    @Operation(summary = "Get Application Status", description = "Returns a simple message indicating that the application is running.")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved status")
    @GetMapping("/status")
    public Map<String, String> getStatus() {
        return Collections.singletonMap("status", "Application is up and running!");
    }
}
