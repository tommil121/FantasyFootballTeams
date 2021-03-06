package com.example.tom.fantasyfootballteams;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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

    SharedPreferences prefs;

    private Team [] teams;


    //create intent object for menu
    Intent intent;
    //responsible for turning data into list items
    //that our activity can use
    ListAdapter adapter;

    String sortBy = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_results);

        prefs = getSharedPreferences(
                "com.example.tom.fantasyfootballteams", Context.MODE_PRIVATE);

        if(!prefs.getString("sort","").equals(null))
            sortBy = prefs.getString("sort", "");
        else
            sortBy = null;

        setContentView(R.layout.activity_team_results);
        dbHandler = new DBHandler(this, null);

        if(sortBy == null)
            teamData = dbHandler.getTeams();
        else if(sortBy.equals("name"))
            teamData = dbHandler.getTeamsByName();
        else if(sortBy.equals("season"))
            teamData = dbHandler.getTeamsBySeason();
        else if(sortBy.equals("league"))
            teamData = dbHandler.getTeamsByLeague();
        else
            teamData = dbHandler.getTeams();

        //change name of page
        setTitle("View My Teams");

        String [] noTeams = {"No teams found!"};





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


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void setSortBy(View v){
        prefs.edit().putString("sort", (String) v.getTag()).apply();

        Intent intent = new Intent(this, TeamResultsActivity.class);
        startActivity(intent);
    }

    public void goToDeleteTeam(View view){
        Intent intent = new Intent(this, delete_team.class);
        startActivity(intent);
    }

    public void goToAddPlayerActivity(View view){

        dbHandler = new DBHandler(this, null);

        teams = dbHandler.getTeams();

        if(teams != null){
            intent = new Intent(this, add_player.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "You must first add a team!",
                    Toast.LENGTH_LONG).show();
        }


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_home :
                intent = new Intent(this, MainActivity.class);

                startActivity(intent);
                return true;

            /**case R.id.menu_create_team :

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
            case R.id.menu_delete_player:

                intent = new Intent(this, delete_player.class);
                startActivity(intent);

                return true; */

            default :
                return super.onOptionsItemSelected(item);
        }
    }
}
