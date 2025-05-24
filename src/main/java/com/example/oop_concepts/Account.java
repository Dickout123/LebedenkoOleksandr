package com.example.oop_concepts;

public class Account {
    private double balance;
    private String accountNumber;

    // Конструктор
    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        // Початковий баланс не може бути від'ємним
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            System.out.println("Початковий баланс не може бути від'ємним. Встановлено 0.");
            this.balance = 0;
        }
    }

    // Гетер для номера рахунку (balance можна отримати через checkBalance)
    public String getAccountNumber() {
        return accountNumber;
    }

    // Метод для поповнення коштів
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Поповнення на " + amount + " грн. Новий баланс: " + balance + " грн.");
        } else {
            System.out.println("Сума поповнення повинна бути позитивною.");
        }
    }

    // Метод для зняття коштів
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Зняття " + amount + " грн. Новий баланс: " + balance + " грн.");
            return true;
        } else if (amount <= 0) {
            System.out.println("Сума зняття повинна бути позитивною.");
            return false;
        } else {
            System.out.println("Недостатньо коштів на рахунку для зняття " + amount + " грн. Поточний баланс: " + balance + " грн.");
            return false;
        }
    }

    // Метод для перевірки балансу
    public double checkBalance() {
        System.out.println("Поточний баланс на рахунку " + accountNumber + ": " + balance + " грн.");
        return balance;
    }
}