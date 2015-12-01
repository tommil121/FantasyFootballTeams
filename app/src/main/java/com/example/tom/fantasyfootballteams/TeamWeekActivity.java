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

public class TeamWeekActivity extends AppCompatActivity {

    public static String team_name;
    public static int weekNum;

    Intent intent;

    DBHandler dbHandler;
    Spinner teamNameSpinner;
    Spinner weekSpinner;

    //For team spinner
    Team [] teams;
    ArrayList<String> teamList;

    Roster [] rosters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_week);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //change name of page
        setTitle("Select to Set Roster & Points");

        teamNameSpinner = (Spinner)findViewById(R.id.team_spinner);
        weekSpinner = (Spinner) findViewById(R.id.weekSpinner);

        dbHandler = new DBHandler(this, null);

        //put all of the teams in an array of Team objects
        teams = dbHandler.getTeams();

        //declare and initialize an array list for strings
        teamList = new ArrayList<String>();


        //go through the list of Team objects and extract the "list friendly" toString method and
        //add them to the array list
        if (teams == null){
            teams = new Team[1];
            teams[0] = new Team("Please add a team!","","");
        }

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
    }

    public void goToRosterActivity(View view){

        weekNum = weekSpinner.getSelectedItemPosition();
        weekNum = weekNum + 1;
        team_name =  teams[teamNameSpinner.getSelectedItemPosition()].getTeamName();



        intent = new Intent(this, add_to_roster.class);
        startActivity(intent);
    }

    public void goToAddPointsActivity(View view){
        weekNum = weekSpinner.getSelectedItemPosition();
        weekNum = weekNum + 1;
        team_name =  teams[teamNameSpinner.getSelectedItemPosition()].getTeamName();
        dbHandler = new DBHandler(this, null);

        rosters = dbHandler.getRosterPlayers(team_name,weekNum);

        if(teams != null){
            intent = new Intent(this, AddPointsActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "You must first add a roster!",
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

        intent = new Intent(this, MainActivity.class);

        startActivity(intent);
        return true;

    }
}
