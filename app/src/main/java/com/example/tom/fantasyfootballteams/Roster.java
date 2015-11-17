package com.example.tom.fantasyfootballteams;

/**
 * Created by Tom on 11/17/2015.
 */
public class Roster {
    private int rosterId;
    private String playerName;
    private String teamName;
    private int week;
    private int pts;

    public Roster(String playerName, String teamName, int week, int pts) {
        this.playerName = playerName;
        this.teamName = teamName;
        this.week = week;
        this.pts = pts;
    }

    public int getRosterId() {
        return rosterId;
    }

    public void setRosterId(int rosterId) {
        this.rosterId = rosterId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getPts() {
        return pts;
    }

    public void setPts(int pts) {
        this.pts = pts;
    }
}
