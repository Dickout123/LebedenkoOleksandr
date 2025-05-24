package com.example.oop_concepts;

public class Notification {
    private String recipient;
    private String message;

    public Notification(String recipient, String message) {
        this.recipient = recipient;
        this.message = message;
    }

    // Гетери для доступу до приватних полів у підкласах (якщо потрібно)
    public String getRecipient() {
        return recipient;
    }

    public String getMessage() {
        return message;
    }

    // Базовий метод відправки повідомлення
    public void send() {
        System.out.println("Відправлення загального повідомлення: '" + message + "' отримувачу: " + recipient);
    }
}