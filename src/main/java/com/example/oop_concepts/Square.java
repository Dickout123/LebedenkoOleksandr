package com.example.oop_concepts;

public class Square extends Shape {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double calculateArea() {
        double area = side * side;
        System.out.println("Площа квадрата: " + area);
        return area;
    }
}