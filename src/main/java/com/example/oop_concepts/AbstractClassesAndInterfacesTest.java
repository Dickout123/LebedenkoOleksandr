package com.example.oop_concepts;

public class AbstractClassesAndInterfacesTest {
    public static void main(String[] args) {
        // Тестування Appliance, WashingMachine, Microwave
        System.out.println("--- Тестування Абстрактних Класів (Appliance & Subclasses) ---");
        // Appliance appliance = new Appliance("Generic", "Model"); // Це неможливо, Appliance абстрактний
        WashingMachine lgWasher = new WashingMachine("LG", "F4WV909P2E", 9);
        lgWasher.displayBasicInfo();
        lgWasher.turnOn();

        Microwave samsungMW = new Microwave("Samsung", "MS23F301TAS", 800);
        samsungMW.displayBasicInfo();
        samsungMW.turnOn();

        System.out.println("\n");

        // Тестування Playable, Guitar, Piano
        System.out.println("--- Тестування Інтерфейсів (Playable & Implementations) ---");
        // Playable instrument = new Playable(); // Це неможливо, Playable - інтерфейс

        Guitar acousticGuitar = new Guitar("Акустична");
        acousticGuitar.play();

        Piano grandPiano = new Piano(88);
        grandPiano.play();

        // Поліморфізм з інтерфейсом
        System.out.println("\n--- Поліморфізм з інтерфейсом Playable ---");
        Playable[] instruments = new Playable[2];
        instruments[0] = new Guitar("Електрична");
        instruments[1] = new Piano(61);

        for (Playable inst : instruments) {
            inst.play();
        }
    }
}