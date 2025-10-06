-- This script will be run automatically by Spring Boot on startup
-- It ensures the food_items table exists and is populated with data.

-- Drop the table if it exists to start fresh
DROP TABLE IF EXISTS food_items;

-- Create the food_items table
CREATE TABLE food_items (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

-- Insert 10 food items into the table
INSERT INTO food_items (name, price) VALUES
('Margherita Pizza', 8.99),
('Cheeseburger', 5.49),
('Caesar Salad', 7.25),
('Spaghetti Carbonara', 12.50),
('Chicken Tacos (3)', 9.99),
('Sushi Platter', 15.00),
('Vegetable Stir-fry', 10.75),
('Fish and Chips', 11.20),
('Chocolate Lava Cake', 6.50),
('Iced Coffee', 3.75);
