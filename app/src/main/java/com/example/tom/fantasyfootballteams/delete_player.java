package com.example.tom.fantasyfootballteams;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class delete_player extends AppCompatActivity {

    Intent intent;

    DBHandler dbHandler;
    Spinner playerSpinner;

    //For team spinner
    Player [] players;
    ArrayList<String> playerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_player);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //change name of page
        setTitle("Delete a Player");

        playerSpinner = (Spinner)findViewById(R.id.select_player_spinner);

        dbHandler = new DBHandler(this, null);

        //put all of the teams in an array of Player objects
        players = dbHandler.getPlayers();

        //declare and initialize an array list for strings
        playerList = new ArrayList<String>();

        if(players == null) {
            players = new Player[1];
            players[0] = new Player("Please", " player ", "add a");
        }


        //go through the list of Team objects and extract the "list friendly" toString method and
        //add them to the array list
        for(int i = 0; i < players.length; i++){
            playerList.add(players[i].toString());
        }

        //create an adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, playerList);

        //set the adapter to fill the list
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //apply the adapter to the spinner
        playerSpinner.setAdapter(adapter);
    }

    public void deletePlayerData(View view) {
        String playerName = playerSpinner.getItemAtPosition(playerSpinner.getFirstVisiblePosition()).toString();
        Player toDelete = null;

        players = dbHandler.getPlayers();

        for(int i = 0; i < players.length; i++){
            if(playerName.equals(players[i].toString())){
                toDelete = players[i];
            }
        }

        dbHandler.deletePlayer(toDelete);

        intent = new Intent(this, delete_player.class);
        startActivity(intent);


        Toast.makeText(this, "Player Deleted!",
                Toast.LENGTH_LONG).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        intent = new Intent(this, MainActivity.class);

        startActivity(intent);
        return true;

    }
}
