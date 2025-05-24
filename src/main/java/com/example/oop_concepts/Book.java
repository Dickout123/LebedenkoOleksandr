package com.example.oop_concepts;

public class Book {
    private String title;
    private String author;
    private int year;

    // Конструктор
    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    // Гетери (Getters)
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    // Сетери (Setters)
    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        // Додаємо базову валідацію для року
        if (year > 0 && year <= java.time.Year.now().getValue()) {
            this.year = year;
        } else {
            System.out.println("Некоректний рік: " + year);
        }
    }

    // Метод для виведення інформації про книгу
    public void displayInfo() {
        System.out.println("--- Інформація про книгу ---");
        System.out.println("Назва: " + title);
        System.out.println("Автор: " + author);
        System.out.println("Рік видання: " + year);
        System.out.println("----------------------------");
    }
}