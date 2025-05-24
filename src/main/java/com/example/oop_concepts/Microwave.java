package com.example.oop_concepts;

public class Microwave extends Appliance {
    private int powerWatts; // Потужність (Ватт)

    public Microwave(String brand, String model, int powerWatts) {
        super(brand, model);
        this.powerWatts = powerWatts;
    }

    public int getPowerWatts() {
        return powerWatts;
    }

    @Override
    public void turnOn() {
        System.out.println(getBrand() + " " + getModel() + " мікрохвильова піч увімкнена з потужністю " + powerWatts + " Вт.");
    }
}