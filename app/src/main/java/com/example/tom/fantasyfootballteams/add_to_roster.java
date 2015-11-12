package com.example.tom.fantasyfootballteams;

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

import java.util.ArrayList;

public class add_to_roster extends AppCompatActivity {

    //declare spinners
    Spinner teamSpinner;
    Spinner qbSpinner;
    Spinner rbSpinner;
    Spinner wrSpinner;
    Spinner teSpinner;
    Spinner kSpinner;

    //declare dbhandler
    DBHandler dbHandler;

    //declare player arrays for getplayers return
    Player [] qb;
    Player [] rb;
    Player [] wr;
    Player [] te;
    Player [] k;


    //declare and initialize arraylists for use with adapter
    ArrayList<String> qbList = new ArrayList<String>();
    ArrayList<String> rbList = new ArrayList<String>();
    ArrayList<String> wrList = new ArrayList<String>();
    ArrayList<String> teList = new ArrayList<String>();
    ArrayList<String> kList = new ArrayList<String>();







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

        dbHandler = new DBHandler(this, null);



        //Create a listener to listen to when an item is selected
        teamSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View v, int position, long id) {
                Log.v("Teams", "Teams selected");

                //get the team name that was selected
                String team = teamSpinner.getItemAtPosition(position).toString();

                //get players for each position
                qb = dbHandler.getPlayersWhere("TEAM_NAME", "=", team, "TEAM_POSITION", "=", "QB");
                rb = dbHandler.getPlayersWhere("TEAM_NAME", "=", team, "TEAM_POSITION", "=", "RB");
                wr = dbHandler.getPlayersWhere("TEAM_NAME", "=", team, "TEAM_POSITION", "=", "WR");
                te = dbHandler.getPlayersWhere("TEAM_NAME", "=", team, "TEAM_POSITION", "=", "TE");
                k = dbHandler.getPlayersWhere("TEAM_NAME", "=", team, "TEAM_POSITION", "=", "K");


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

                //get the players into their respective arrays
                for(int i = 0; i < qb.length; i++){
                    qbList.add(qb[i].toString());
                }

                for(int i = 0; i < rb.length; i++){
                    qbList.add(rb[i].toString());
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

                //set it to fill the dropdown
                qbAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                rbAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                wrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                teAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                kAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                qbSpinner.setAdapter(qbAdapter);
                rbSpinner.setAdapter(rbAdapter);
                wrSpinner.setAdapter(wrAdapter);
                teSpinner.setAdapter(teAdapter);
                kSpinner.setAdapter(kAdapter);




            }




            public void onNothingSelected(AdapterView<?> arg0) {
                Log.v("Teams", "nothing selected");
            }

        });
        }

    }


