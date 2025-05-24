package com.example.oop_concepts;

public class Manager extends Employee {
    private int teamSize; // Розмір команди, якою керує менеджер

    // Конструктор
    public Manager(String name, String position, int teamSize) {
        super(name, position); // Виклик конструктора базового класу Employee
        this.teamSize = teamSize;
    }

    // Гетер
    public int getTeamSize() {
        return teamSize;
    }

    // Перевизначення методу displayInfo() базового класу
    @Override
    public void displayInfo() {
        super.displayInfo(); // Виклик методу displayInfo() базового класу
        System.out.println("Керує командою з " + teamSize + " осіб.");
        System.out.println("---------------------------------");
    }
}