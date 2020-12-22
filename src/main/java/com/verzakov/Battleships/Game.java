package com.verzakov.Battleships;

import java.util.ArrayList;
import java.util.List;

public class Game {
    List<Player> playersList = new ArrayList<>();
    private int lastShotStatus = -1;

    public void addPlayer(Player player) {
        playersList.add(player);
    }

    public List<Player> getPlayersList() {
        return playersList;
    }

    public int getLastShotStatus() {
        return lastShotStatus;
    }

    public void setLastShotStatus(int lastShotStatus) {
        this.lastShotStatus = lastShotStatus;
    }
}
