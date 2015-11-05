package com.example.tom.fantasyfootballteams;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class delete_team extends AppCompatActivity {

    Intent intent;

    DBHandler dbHandler;
    Spinner teamNameSpinner;

    //For team spinner
    Team [] teams;
    ArrayList<String> teamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_team);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        teamNameSpinner = (Spinner)findViewById(R.id.select_team_spinner);

        dbHandler = new DBHandler(this, null);

        //put all of the teams in an array of Team objects
        teams = dbHandler.getTeams();

        //declare and initialize an array list for strings
        teamList = new ArrayList<String>();


        //go through the list of Team objects and extract the "list friendly" toString method and
        //add them to the array list
        for(int i = 0; i < teams.length; i++){
            teamList.add(teams[i].toStringPlayer());
        }

        //create an adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, teamList);

        //set the adapter to fill the list
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //apply the adapter to the spinner
        teamNameSpinner.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void deleteTeamData(View view) {
        String teamName = teamNameSpinner.getItemAtPosition(teamNameSpinner.getFirstVisiblePosition()).toString();
        Team toDelete = null;

        teams = dbHandler.getTeams();

        for(int i = 0; i < teams.length; i++){
            if(teamName.equals(teams[i].toStringPlayer())){
                toDelete = teams[i];
            }
        }

        dbHandler.deleteTeam(toDelete);

        intent = new Intent(this, delete_team.class);
        startActivity(intent);

    }

}
