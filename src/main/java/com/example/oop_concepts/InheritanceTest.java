package com.example.oop_concepts;

public class InheritanceTest {
    public static void main(String[] args) {
        // Тестування Vehicle та Motorcycle
        System.out.println("--- Тестування Наслідування (Vehicle & Motorcycle) ---");
        Vehicle myCar = new Vehicle("Toyota", "Camry");
        myCar.displayInfo();

        Motorcycle myBike = new Motorcycle("Honda", "CBR500R", 500);
        myBike.displayInfo(); // Викличе перевизначений метод

        System.out.println("\n");

        // Тестування Employee та Manager
        System.out.println("--- Тестування Наслідування (Employee & Manager) ---");
        Employee emp1 = new Employee("Олександр", "Розробник");
        emp1.displayInfo();

        Manager mgr1 = new Manager("Марина", "Менеджер проекту", 15);
        mgr1.displayInfo(); // Викличе перевизначений метод
    }
}