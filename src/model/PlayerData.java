package model;

import java.io.Serializable;
import java.util.Date;

public class PlayerData implements Serializable {
    private String playerName;
    private int score;
    private Date date;

    public PlayerData(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
        this.date = new Date(); // Fecha actual
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public Date getDate() {
        return date;
    }
}