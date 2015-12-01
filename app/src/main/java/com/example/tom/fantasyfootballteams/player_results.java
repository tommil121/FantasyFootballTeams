package com.example.tom.fantasyfootballteams;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.content.SharedPreferences;



public class player_results extends AppCompatActivity {
    //an array of player data
    private Player [] playerData;

    DBHandler dbHandler;

    //responsible for turning data into list items
    //that our activity can use
    ListAdapter adapter;

    SharedPreferences prefs;

    //variable that tells how to sort
    String sortBy ;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //change name of page
        setTitle("View My Players");

        prefs = getSharedPreferences(
                "com.example.tom.fantasyfootballteams", Context.MODE_PRIVATE);

        if(!prefs.getString("sort","").equals(null))
            sortBy = prefs.getString("sort", "");
        else
            sortBy = null;


        setContentView(R.layout.activity_player_result);
        dbHandler = new DBHandler(this, null);

        String [] noPlayers = {"No players found!"};

        //figure out the sorting
        if(sortBy == null)
            playerData = dbHandler.getPlayers();
        else if(sortBy.equals("name"))
            playerData = dbHandler.getPlayersByName();
        else if(sortBy.equals("position"))
            playerData = dbHandler.getPlayersByPosition();
        else if(sortBy.equals("team"))
            playerData = dbHandler.getPlayersByTeam();

        if (playerData != null){
            adapter = new player_results_adapter(this, playerData);
        } else {
            //Set out List Adpater to an ArrayAdapter which converts array
            // to List Items, in this case List Items are Strings
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, noPlayers);
        }

        //create ListView object
        ListView playerListView = (ListView) findViewById(R.id.playerListView);

        //set adapater on playerListView
        playerListView.setAdapter(adapter);

        //create listener to generate the Player ToString on user tap
        /*playerListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String name = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(player_results.this, name, Toast.LENGTH_LONG).show();
                    }
                }
        );
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View view) {
             //   Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
               //         .setAction("Action", null).show();
            //}
       // });*/
    }


    public void setSortBy(View v){
        prefs.edit().putString("sort", (String) v.getTag()).apply();

        Intent intent = new Intent(this, player_results.class);
        startActivity(intent);
    }

    public void goToTeamWeekActivity(View view){
        intent = new Intent(this, TeamWeekActivity.class);
        startActivity(intent);
    }

    public void goToDeletePlayer(View view){
        intent = new Intent(this, delete_player.class);
        startActivity(intent);
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
