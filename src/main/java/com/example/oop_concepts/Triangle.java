package com.example.oop_concepts;

public class Triangle extends Shape {
    private double base;
    private double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        double area = 0.5 * base * height;
        System.out.println("Площа трикутника: " + area);
        return area;
    }
}