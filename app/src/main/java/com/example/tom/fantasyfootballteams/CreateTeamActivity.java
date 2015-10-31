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

    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);

        teamNameEditText = (EditText)findViewById(R.id.teamNameEditText);
        seasonEditText = (EditText)findViewById(R.id.seasonEditText);
        leagueNameEditText = (EditText)findViewById(R.id.leagueNameEditText);

        dbHandler = new DBHandler(this, null);

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

    public void goToTeam (View view){

        //change to run after button click submit team
        String name = leagueNameEditText.getText().toString();

        if (name.trim().equals(""))
            //toast formats a message that displays in the app
            Toast.makeText(this, "Please enter a team name!", Toast.LENGTH_LONG).show();
        else {
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("team name", name);
            startActivity(intent);
        }
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

            default :
                return super.onOptionsItemSelected(item);
        }
    }

}
