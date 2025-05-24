package com.example.oop_concepts;

public class PolymorphismTest {
    public static void main(String[] args) {
        // Тестування Shape, Triangle, Square
        System.out.println("--- Тестування Поліморфізму (Shape & Subclasses) ---");
        Shape shape1 = new Shape();
        shape1.calculateArea(); // Викличе метод базового класу

        Shape triangle1 = new Triangle(10, 5); // Об'єкт Triangle, але посилаємось на нього як на Shape
        triangle1.calculateArea(); // Викличе перевизначений метод Triangle

        Shape square1 = new Square(7); // Об'єкт Square, але посилаємось на нього як на Shape
        square1.calculateArea(); // Викличе перевизначений метод Square

        System.out.println("\n");

        // Тестування Notification, EmailNotification, SMSNotification
        System.out.println("--- Тестування Поліморфізму (Notification & Subclasses) ---");
        Notification generalNotif = new Notification("user@example.com", "Це загальне повідомлення.");
        generalNotif.send();

        Notification emailNotif = new EmailNotification("admin@example.com", "Ваше замовлення #123 оброблено.", "Підтвердження замовлення");
        emailNotif.send(); // Викличе перевизначений метод EmailNotification

        Notification smsNotif = new SMSNotification("+380991234567", "Ваш баланс: 150 грн.", "0671234567");
        smsNotif.send(); // Викличе перевизначений метод SMSNotification

        // Приклад використання поліморфізму у масиві
        System.out.println("\n--- Поліморфізм у масиві ---");
        Notification[] notifications = new Notification[3];
        notifications[0] = new Notification("common@example.com", "Привіт.");
        notifications[1] = new EmailNotification("support@example.com", "Нове запитання.", "Запит підтримки");
        notifications[2] = new SMSNotification("+380639876543", "Нагадування про зустріч.", "MyBank");

        for (Notification notif : notifications) {
            notif.send(); // Залежно від фактичного типу об'єкта буде викликано відповідний метод send()
            System.out.println();
        }
    }
}