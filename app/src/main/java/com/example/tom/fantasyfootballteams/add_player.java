package com.example.tom.fantasyfootballteams;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import java.util.ArrayList;

public class add_player extends AppCompatActivity {

    Intent intent;

    EditText playerNameEditText;
    Spinner teamNameSpinner;
    Spinner positionSpinner;
    DBHandler dbHandler;

    //For team spinner
    Team [] teams;
    ArrayList<String> teamList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        playerNameEditText = (EditText) findViewById(R.id.player_name);
        teamNameSpinner = (Spinner)findViewById(R.id.team_name_spinner);
        positionSpinner = (Spinner)findViewById(R.id.position_spinner);

        dbHandler = new DBHandler(this, null);

        //Fill team spinner code
        teams = dbHandler.getTeams();

        teamList = new ArrayList<String>();



        for(int i = 0; i < teams.length; i++){
            teamList.add(teams[i].toStringPlayer());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, teamList);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        teamNameSpinner.setAdapter(adapter);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        }

    public void goToPlayerResultsActivity(View view){
        intent = new Intent(this, player_results.class);
        startActivity(intent);
    }

    public void addData (View view){

        String playerName = playerNameEditText.getText().toString();
        String position = positionSpinner.getItemAtPosition(positionSpinner.getFirstVisiblePosition()).toString();
        String playerTeamName = teamNameSpinner.getItemAtPosition(teamNameSpinner.getFirstVisiblePosition()).toString();

        //Player newPlayer = new Player(playerName, playerTeamName, position);


        if (playerName.trim().equals("") || position.trim().equals("")
                || playerTeamName.trim().equals(""))
            Toast.makeText(this, "Please enter a Player name, valid team name, and position!",
                    Toast.LENGTH_LONG).show();

        else {
            dbHandler.addPlayer(playerName, position, playerTeamName);
            Toast.makeText(this, "Player added!",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_home :
                intent = new Intent(this, MainActivity.class);

                startActivity(intent);
                return true;

            case R.id.menu_create_team :

                intent = new Intent(this, CreateTeamActivity.class);
                startActivity(intent);

                return true;

            case R.id.menu_add_player:

                intent = new Intent(this, add_player.class);
                startActivity(intent);

                return true;
            case R.id.menu_delete_team:

                intent = new Intent(this, delete_team.class);
                startActivity(intent);

                return true;

            default :
                return super.onOptionsItemSelected(item);
        }
    }
}
