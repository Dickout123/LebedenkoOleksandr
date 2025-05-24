package com.example.oop_concepts;

public class WashingMachine extends Appliance {
    private int loadCapacity; // Ємність завантаження (кг)

    public WashingMachine(String brand, String model, int loadCapacity) {
        super(brand, model);
        this.loadCapacity = loadCapacity;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    @Override
    public void turnOn() {
        System.out.println(getBrand() + " " + getModel() + " пральна машина увімкнена. Готова до завантаження " + loadCapacity + " кг білизни.");
    }
}