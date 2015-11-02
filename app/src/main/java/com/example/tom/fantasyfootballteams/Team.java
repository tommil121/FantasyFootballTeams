package com.example.tom.fantasyfootballteams;

/**
 * Created by Tom on 10/31/2015.
 */
public class Team {
    private int id;
    private String teamName;
    private String season;
    private String leagueName;

    public Team(){

    }

    public Team(String teamName, String season, String leagueName){
        this.teamName = teamName;
        this.season = season;
        this.leagueName = leagueName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    @Override
    public String toString() {
        return "Team Name : " + teamName + "\n" +
                "Season : " + season + "\n" +
                "League Name : " + leagueName;
    }
}
