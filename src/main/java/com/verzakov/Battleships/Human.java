package com.verzakov.Battleships;

public class Human implements Player{
    String name;
    private final BattleField field;

    public Human(String name) {
        this.name = name;
        field = new BattleField();
    }

    @Override
    public void Shoot() {

    }

    @Override
    public String getName() {
        return name;
    }

    public BattleField getField() {
        return field;
    }
}
