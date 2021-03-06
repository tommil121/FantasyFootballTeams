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

public class MainActivity extends AppCompatActivity {

    Intent intent;

    Team teams[] = null;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //change name of page
        setTitle("Fantasy Football Teams");
    }

    public void goToCreateTeam(View view){
        intent = new Intent(this, CreateTeamActivity.class);
        startActivity(intent);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            //set intent of menu to navigate to main_activity
            case R.id.menu_home:

                intent = new Intent(this, MainActivity.class);

                startActivity(intent);
                return true;

            //set intent of menu to navigate to create_team_activity

            /**case R.id.menu_create_team:

                intent = new Intent(this, CreateTeamActivity.class);
                startActivity(intent);

                return true;

            //set intent of menu to navigate to add_player_activity
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
            case R.id.menu_add_to_roster:

                intent = new Intent(this, add_to_roster.class);
                startActivity(intent);

                return true;
            case R.id.menu_add_points:

                intent = new Intent(this, AddPointsActivity.class);
                startActivity(intent);

                return true;
            case R.id.menu_team_week_activity:

                intent = new Intent(this, TeamWeekActivity.class);
                startActivity(intent);

                return true; */
        }

        return super.onOptionsItemSelected(item);
    }
}
