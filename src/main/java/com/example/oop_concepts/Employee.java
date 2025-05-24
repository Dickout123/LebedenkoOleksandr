package com.example.oop_concepts;

public class Employee {
    private String name;
    private String position;

    // Конструктор
    public Employee(String name, String position) {
        this.name = name;
        this.position = position;
    }

    // Гетери
    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    // Метод для виведення інформації про працівника
    public void displayInfo() {
        System.out.println("--- Інформація про працівника ---");
        System.out.println("Ім'я: " + name);
        System.out.println("Посада: " + position);
    }
}