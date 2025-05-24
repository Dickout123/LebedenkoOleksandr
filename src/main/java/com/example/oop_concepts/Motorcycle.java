package com.example.oop_concepts;

public class Motorcycle extends Vehicle {
    private int engineCapacity; // Об'єм двигуна (куб. см)

    // Конструктор
    public Motorcycle(String make, String model, int engineCapacity) {
        super(make, model); // Виклик конструктора базового класу Vehicle
        this.engineCapacity = engineCapacity;
    }

    // Гетер
    public int getEngineCapacity() {
        return engineCapacity;
    }

    // Перевизначення методу displayInfo() базового класу
    @Override
    public void displayInfo() {
        super.displayInfo(); // Виклик методу displayInfo() базового класу
        System.out.println("Об'єм двигуна: " + engineCapacity + " куб. см");
        System.out.println("------------------------------------");
    }
}