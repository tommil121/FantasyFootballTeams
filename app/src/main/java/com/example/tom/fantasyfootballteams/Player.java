package com.example.tom.fantasyfootballteams;

/**
 * Created by Dustin on 11/2/15.
 */
public class Player {

    private int id;
    private String name;
    private String nflTeamName;
    private String positionName;
    private int teamId;

    public Player(String name, String nflTeamName, String positionName, int teamId) {
        this.name = name;
        this.nflTeamName = nflTeamName;
        this.positionName = positionName;
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNflTeamName() {
        return nflTeamName;
    }

    public void setNflTeamName(String nflTeamName) {
        this.nflTeamName = nflTeamName;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }
}
