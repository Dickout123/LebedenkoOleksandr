package com.example.oop_concepts;

public class EmailNotification extends Notification {
    private String subject;

    public EmailNotification(String recipient, String message, String subject) {
        super(recipient, message);
        this.subject = subject;
    }

    @Override
    public void send() {
        System.out.println("Відправлення email-повідомлення...");
        System.out.println("Тема: " + subject);
        System.out.println("Отримувач: " + getRecipient()); // Використовуємо гетер з базового класу
        System.out.println("Повідомлення: " + getMessage()); // Використовуємо гетер з базового класу
        System.out.println("--- Email відправлено ---");
    }

    // Можна додати гетери для recipient та message в базовий клас Notification
    // public String getRecipient() { return super.getRecipient(); } // Якщо вони були б private в Notification
    // public String getMessage() { return super.getMessage(); }
}