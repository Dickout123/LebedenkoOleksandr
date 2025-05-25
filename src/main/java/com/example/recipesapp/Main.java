package com.example.recipesapp; // Переконайтеся, що пакет відповідає шляху до файлу

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main { // Може бути RecipesAppApplication або інша назва, залежить від створення

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println("Hello and Welcome!"); // Ваш тестовий рядок
    }

}