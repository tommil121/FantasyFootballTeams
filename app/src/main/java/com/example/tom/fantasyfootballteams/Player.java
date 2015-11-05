package com.example.tom.fantasyfootballteams;

import android.widget.SimpleCursorAdapter;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString(){
        return (this.name + "   " + this.positionName);
    }

    public String toStringWithTeam(Team team){
        return (this.toString() + "   " + team.toStringPlayer());
    }
}