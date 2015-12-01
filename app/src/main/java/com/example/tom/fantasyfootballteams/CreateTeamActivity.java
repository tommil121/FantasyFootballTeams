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
import android.widget.EditText;
import android.widget.Toast;

public class CreateTeamActivity extends AppCompatActivity {

    //reference variables
    EditText teamNameEditText;
    EditText seasonEditText;
    EditText leagueNameEditText;

    Intent intent;

    Team teams[] = null;

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);

        //change name of page
        setTitle("Create a New Team");

        teamNameEditText = (EditText)findViewById(R.id.teamNameEditText);
        seasonEditText = (EditText)findViewById(R.id.seasonEditText);
        leagueNameEditText = (EditText)findViewById(R.id.leagueNameEditText);

        dbHandler = new DBHandler(this, null);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void addData (View view){

        String teamName = teamNameEditText.getText().toString();
        String season = seasonEditText.getText().toString();
        String leagueName = leagueNameEditText.getText().toString();

        if (teamName.trim().equals("") || season.trim().equals("")
                || leagueName.trim().equals(""))
            Toast.makeText(this, "Please enter a team name, season, and league name!",
                    Toast.LENGTH_LONG).show();

        else {
            dbHandler.addTeam(teamName, season, leagueName);
            Toast.makeText(this, "Team added!",
                    Toast.LENGTH_LONG).show();
        }
    }





    public void goToTeamResultsActivity(View view){
        dbHandler = new DBHandler(this, null);
        teams = dbHandler.getTeams();

        if(teams != null){
            intent = new Intent(this, TeamResultsActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "You must first add a team!",
                    Toast.LENGTH_LONG).show();
        }
    }

    //java code for button to go to Add Player Activity
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

                return true;
            */
            default :
                return super.onOptionsItemSelected(item);
        }
    }

}
