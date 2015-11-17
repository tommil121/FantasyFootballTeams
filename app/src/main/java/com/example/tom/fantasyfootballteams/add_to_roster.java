package com.example.tom.fantasyfootballteams;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class add_to_roster extends AppCompatActivity {

    String oldTeam = null;

    //declare spinners
    Spinner teamSpinner;
    Spinner qbSpinner;
    Spinner rbSpinner;
    Spinner wrSpinner;
    Spinner teSpinner;
    Spinner kSpinner;
    Spinner dstSpinner;

    //declare dbhandler
    DBHandler dbHandler;

    //declare player arrays for getplayers return
    Team [] teams;
    Player [] qb;
    Player [] rb;
    Player [] wr;
    Player [] te;
    Player [] k;
    Player [] dst;


    //declare and initialize arraylists for use with adapter
    ArrayList<String> teamList;
    ArrayList<String> qbList = new ArrayList<String>();
    ArrayList<String> rbList = new ArrayList<String>();
    ArrayList<String> wrList = new ArrayList<String>();
    ArrayList<String> teList = new ArrayList<String>();
    ArrayList<String> kList = new ArrayList<String>();
    ArrayList<String> dstList = new ArrayList<String>();







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_roster);
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

        //initialize spinners
        teamSpinner = (Spinner)findViewById(R.id.team_selector);
        qbSpinner = (Spinner) findViewById(R.id.qb_spinner);
        rbSpinner = (Spinner) findViewById(R.id.rb_spinner);
        wrSpinner = (Spinner) findViewById(R.id.wr_spinner);
        teSpinner = (Spinner) findViewById(R.id.te_spinner);
        kSpinner = (Spinner) findViewById(R.id.k_spinner);
        dstSpinner = (Spinner) findViewById(R.id.dst_spinner);

        dbHandler = new DBHandler(this, null);



        //put all of the teams in an array of Team objects
        teams = dbHandler.getTeams();

        //declare and initialize an array list for strings
        teamList = new ArrayList<String>();

        if(teams == null) {
            teams = new Team[1];
            teams[0] = new Team("Please", " add a ", "team");
        }
        //go through the list of Team objects and extract the "list friendly" toString method and
        //add them to the array list
        for(int i = 0; i < teams.length; i++){
            teamList.add(teams[i].toStringPlayer());
        }

        //create an adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, teamList);

        //set the adapter to fill the list
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //apply the adapter to the spinner
        teamSpinner.setAdapter(adapter);


        //THIS LISTENER WILL BECOME USELESS. The week will be selected via a button. The select team
        //     is insignifcant
        //Create a listener to listen to when an item is selected
        teamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View v, int position, long id) {
                Log.v("Teams", "Teams selected");

                //get the team name that was selected
                String team = teams[position].getTeamName();


                displayToast(team);

                //get players for each position
                qb = dbHandler.getPlayersWhere(team, "PLAYER_POSITION", "=", "QB");
                rb = dbHandler.getPlayersWhere(team, "PLAYER_POSITION", "=", "RB");
                wr = dbHandler.getPlayersWhere(team, "PLAYER_POSITION", "=", "WR");
                te = dbHandler.getPlayersWhere(team, "PLAYER_POSITION", "=", "TE");
                k = dbHandler.getPlayersWhere(team, "PLAYER_POSITION", "=", "K");
                dst = dbHandler.getPlayersWhere(team, "PLAYER_POSITION", "=", "DST");


                //error checking
                if(qb == null) {
                    qb = new Player[1];
                    qb[0] = new Player("You MUST add players to the selected team", "", "");
                }

                if(rb == null) {
                    rb = new Player[1];
                    rb[0] = new Player("You MUST add players to the selected team", "", "");
                }

                if(wr == null) {
                    wr = new Player[1];
                    wr[0] = new Player("You MUST add players to the selected team", "", "");
                }

                if(te == null) {
                    te = new Player[1];
                    te[0] = new Player("You MUST add players to the selected team", "", "");
                }

                if(k == null) {
                    k = new Player[1];
                    k[0] = new Player("You MUST add players to the selected team", "", "");
                }

                if(dst == null) {
                    dst = new Player[1];
                    dst[0] = new Player("You MUST add players to the selected team", "", "");
                }

                //get the players into their respective arrays
                for(int i = 0; i < qb.length; i++){
                    qbList.add(qb[i].toString());
                }

                for(int i = 0; i < rb.length; i++){
                    rbList.add(rb[i].toString());
                }

                for(int i = 0; i < wr.length; i++){
                    wrList.add(wr[i].toString());
                }

                for(int i = 0; i < te.length; i++){
                    teList.add(te[i].toString());
                }

                for(int i = 0; i < k.length; i++){
                    kList.add(k[i].toString());
                }

                for(int i = 0; i < dst.length; i++){
                    dstList.add(dst[i].toString());
                }

                //Create adapters
                ArrayAdapter<String> qbAdapter = new ArrayAdapter<String>(
                        add_to_roster.this, android.R.layout.simple_spinner_item, qbList);

                ArrayAdapter<String> rbAdapter = new ArrayAdapter<String>(
                        add_to_roster.this, android.R.layout.simple_spinner_item, rbList);

                ArrayAdapter<String> wrAdapter = new ArrayAdapter<String>(
                        add_to_roster.this, android.R.layout.simple_spinner_item, wrList);

                ArrayAdapter<String> teAdapter = new ArrayAdapter<String>(
                        add_to_roster.this, android.R.layout.simple_spinner_item, teList);

                ArrayAdapter<String> kAdapter = new ArrayAdapter<String>(
                        add_to_roster.this, android.R.layout.simple_spinner_item, kList);

                ArrayAdapter<String> dstAdapter = new ArrayAdapter<String>(
                        add_to_roster.this, android.R.layout.simple_spinner_item, dstList);

                //set it to fill the dropdown
                qbAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                rbAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                wrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                teAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                kAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                dstAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                qbSpinner.setAdapter(qbAdapter);
                rbSpinner.setAdapter(rbAdapter);
                wrSpinner.setAdapter(wrAdapter);
                teSpinner.setAdapter(teAdapter);
                kSpinner.setAdapter(kAdapter);
                dstSpinner.setAdapter(dstAdapter);





                goToPlayerResultsActivity(team);

            }




            public void onNothingSelected(AdapterView<?> arg0) {
                Log.v("Teams", "nothing selected");
            }

        });
        }

    public void displayToast(String name){
        Toast.makeText(this, name,
                Toast.LENGTH_LONG).show();

    }

    public void goToPlayerResultsActivity(String newTeam){
        if(oldTeam == null)
            oldTeam = newTeam;

        if(!oldTeam.equals(newTeam)){
            Intent intent = new Intent(this, add_to_roster.class);
            startActivity(intent);

        }


    }

    public void addToRoster(){

    }


}


