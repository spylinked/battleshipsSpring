package com.verzakov.Battleships;

public class Human implements Player{
    String name;
    private final BattleField field;

    public Human(String name) {
        this.name = name;
        field = new BattleField();
    }

    @Override
    public int shoot(Player player) {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

    public BattleField getField() {
        return field;
    }

    @Override
    public void placeShips() {

    }
}
