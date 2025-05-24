package com.example.oop_concepts;

// Абстрактний клас Appliance
public abstract class Appliance {
    private String brand;
    private String model;

    public Appliance(String brand, String model) {
        this.brand = brand;
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    // Абстрактний метод, який повинен бути реалізований у підкласах
    public abstract void turnOn();

    // Можна додати звичайні методи, які не є абстрактними
    public void displayBasicInfo() {
        System.out.println("Пристрій: " + brand + " " + model);
    }
}