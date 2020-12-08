package com.verzakov.Battleships;

import java.util.ArrayList;
import java.util.List;

public class Game {
    List<Player> playersList = new ArrayList<>();

    public void addPlayer(Player player) {
        playersList.add(player);
    }

    public List<Player> getPlayersList() {
        return playersList;
    }
}
