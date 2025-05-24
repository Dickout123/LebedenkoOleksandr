package com.example.servletdemo;

public class Smartphone {
    private String name;
    private String image;
    private String description;
    private double price;
    private String brand;

    public Smartphone(String name, String image, String description, double price, String brand) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.brand = brand;
    }

    // Гетери (сетери можна додати за потреби)
    public String getName() { return name; }
    public String getImage() { return image; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public String getBrand() { return brand; }
}