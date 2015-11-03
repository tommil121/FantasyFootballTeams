package com.example.tom.fantasyfootballteams;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;



public class player_results extends AppCompatActivity {
    //an array of player data
    private Player [] playerData;

    DBHandler dbHandler;

    //responsible for turning data into list items
    //that our activity can use
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_player_result);
        dbHandler = new DBHandler(this, null);

        String [] noPlayers = {"No players found!"};

        playerData = dbHandler.getPlayers();

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
        ); */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


}
