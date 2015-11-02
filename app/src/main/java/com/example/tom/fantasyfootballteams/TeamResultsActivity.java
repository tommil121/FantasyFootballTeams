package com.example.tom.fantasyfootballteams;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TeamResultsActivity extends AppCompatActivity {

    //an array of team data
    private Team [] teamData;

    DBHandler dbHandler;

    //responsible for turning data into list items
    //that our activity can use
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_results);

        dbHandler = new DBHandler(this, null);

        String [] noTeams = {"No teams found!"};

        teamData = dbHandler.getTeams();

        if (teamData != null){
            adapter = new TeamResultsAdapter(this, teamData);
        } else {
            //Set out List Adpater to an ArrayAdapter which converts array
            // to List Items, in this case List Items are Strings
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, noTeams);
        }

        //create ListView object
        ListView teamListView = (ListView) findViewById(R.id.teamListView);

        //set adapater on teamListView
        teamListView.setAdapter(adapter);

        //create listener to generate the Team ToString on user tap
        teamListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String name = String.valueOf(parent.getItemAtPosition(position));
                        Toast.makeText(TeamResultsActivity.this, name, Toast.LENGTH_LONG).show();
                    }
                }
        );
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
