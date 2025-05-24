package com.example.oop_concepts;

public class Shape {
    // Базовий метод для обчислення площі.
    // Він буде перевизначений у підкласах.
    public double calculateArea() {
        System.out.println("Обчислення площі для загальної фігури.");
        return 0.0; // Загальна фігура не має конкретної площі
    }
}