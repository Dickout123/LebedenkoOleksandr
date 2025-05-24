package com.example.oop_concepts;

public class SMSNotification extends Notification {
    private String senderNumber; // Номер відправника (або просто sender)

    public SMSNotification(String recipient, String message, String senderNumber) {
        super(recipient, message);
        this.senderNumber = senderNumber;
    }

    @Override
    public void send() {
        System.out.println("Відправлення SMS-повідомлення...");
        System.out.println("Відправник: " + senderNumber);
        System.out.println("Отримувач: " + getRecipient());
        System.out.println("Повідомлення: " + getMessage());
        System.out.println("--- SMS відправлено ---");
    }
}