package com.example.oop_concepts;

public class Piano implements Playable {
    private int numberOfKeys;

    public Piano(int numberOfKeys) {
        this.numberOfKeys = numberOfKeys;
    }

    public int getNumberOfKeys() {
        return numberOfKeys;
    }

    @Override
    public void play() {
        System.out.println("Грає піаніно з " + numberOfKeys + " клавішами. Мелодійні акорди!");
    }
}