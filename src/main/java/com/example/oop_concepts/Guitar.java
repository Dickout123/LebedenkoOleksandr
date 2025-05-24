package com.example.oop_concepts;

public class Guitar implements Playable {
    private String type; // Наприклад, "акустична", "електрична"

    public Guitar(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public void play() {
        System.out.println("Грає " + type + " гітара. Дзвінкий звук!");
    }
}