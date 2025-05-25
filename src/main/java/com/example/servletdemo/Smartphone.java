package com.example.servletdemo;

import java.util.UUID; // Додаємо імпорт для UUID

public class Smartphone {
    private String id; // Додаємо поле id
    private String name;
    private String image;
    private String description;
    private double price;
    private String brand;

    // Конструктор без ID (ID генерується автоматично)
    public Smartphone(String name, String image, String description, double price, String brand) {
        this.id = UUID.randomUUID().toString(); // Генеруємо унікальний ID
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.brand = brand;
    }

    // Конструктор з ID (для завантаження з файлу або редагування)
    public Smartphone(String id, String name, String image, String description, double price, String brand) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.price = price;
        this.brand = brand;
    }

    // Гетери
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getBrand() {
        return brand;
    }

    // Сетери
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Smartphone{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                '}';
    }
}