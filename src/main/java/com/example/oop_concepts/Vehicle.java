package com.example.oop_concepts;

public class Vehicle {
    private String make;  // Марка
    private String model; // Модель

    // Конструктор
    public Vehicle(String make, String model) {
        this.make = make;
        this.model = model;
    }

    // Гетери
    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    // Метод для виведення основної інформації
    public void displayInfo() {
        System.out.println("--- Інформація про транспортний засіб ---");
        System.out.println("Марка: " + make);
        System.out.println("Модель: " + model);
    }
}