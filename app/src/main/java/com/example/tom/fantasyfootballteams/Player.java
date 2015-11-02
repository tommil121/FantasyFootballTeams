package com.example.tom.fantasyfootballteams;

/**
 * Created by Dustin on 11/2/15.
 */
public class Player {

    private int id;
    private String name;
    private String positionName;
    private String teamName;

    public Player(String name, String teamName, String positionName) {
        this.name = name;
        this.positionName = positionName;
        this.teamName = teamName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}