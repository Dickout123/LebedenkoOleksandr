package com.example.oop_concepts;

public class EncapsulationTest {
    public static void main(String[] args) {
        // Тестування класу Book
        System.out.println("--- Тестування класу Book ---");
        Book book1 = new Book("Java: The Complete Reference", "Herbert Schildt", 2019);
        book1.displayInfo();

        System.out.println("Змінюємо рік: " + book1.getYear());
        book1.setYear(2022);
        book1.displayInfo();

        book1.setYear(3000); // Спроба встановити некоректний рік
        book1.displayInfo();
        System.out.println("\n");

        // Тестування класу Account
        System.out.println("--- Тестування класу Account ---");
        Account myAccount = new Account("UA123456789", 1000.00);
        myAccount.checkBalance();

        myAccount.deposit(500.00);
        myAccount.checkBalance();

        myAccount.withdraw(200.00);
        myAccount.checkBalance();

        myAccount.withdraw(1500.00); // Спроба зняти більше, ніж є на рахунку
        myAccount.checkBalance();

        myAccount.deposit(-100.00); // Спроба поповнити від'ємною сумою
        myAccount.withdraw(-50.00); // Спроба зняти від'ємною сумою
    }
}