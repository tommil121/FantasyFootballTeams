package com.example.tom.fantasyfootballteams;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class add_to_roster extends AppCompatActivity {

    Spinner teamSpinner;
    Spinner qbSpinner;
    Spinner rbSpinner;
    Spinner wrSpinner;
    Spinner teSpinner;
    Spinner kSpinner;
    DBHandler dbHandler;


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






            }

            public void onNothingSelected(AdapterView<?> arg0) {
                Log.v("Teams", "nothing selected");
            }
        });

    }

}
