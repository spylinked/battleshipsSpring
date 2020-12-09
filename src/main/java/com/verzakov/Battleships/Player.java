package com.verzakov.Battleships;

public interface Player {
    int shoot(Player player);
    String getName();
    BattleField getField();
    void placeShips();

}
