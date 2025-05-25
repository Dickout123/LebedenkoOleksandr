package com.example.recipesapp.model; // Переконайтеся, що пакет відповідає шляху до файлу

import jakarta.persistence.*; // Використовуйте 'jakarta.persistence.*' для Spring Boot 3+
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Автоматично генерує геттери, сеттери, toString, equals, hashCode
@NoArgsConstructor // Генерує конструктор без аргументів
@AllArgsConstructor // Генерує конструктор з усіма аргументами
@Entity // Позначає цей клас як сутність JPA
@Table(name = "recipes") // Вказує ім'я таблиці в базі даних
public class Recipe {

    @Id // Позначає поле як первинний ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Автоматичне генерування ID базою даних
    private Long id;

    @Column(nullable = false) // Поле не може бути null
    private String name;

    @Column(columnDefinition = "TEXT") // Використовуйте TEXT для довших описів
    private String description;

    private String imageUrl; // URL зображення рецепту

    @Column(columnDefinition = "TEXT")
    private String ingredients; // Інгредієнти

    @Column(columnDefinition = "TEXT")
    private String instructions; // Інструкції приготування
}