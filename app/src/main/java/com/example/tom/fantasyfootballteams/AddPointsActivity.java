package com.example.tom.fantasyfootballteams;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class AddPointsActivity extends AppCompatActivity {

    //vars for handling names
    TextView team, qb, rb, wr, te, k, dst, weekNum;
    String teamName;

    int week_num;

    Roster [] rosters;


    //create EditText variables for score input
    EditText qbEditText, rbEditText, wrEditText, teEditText,
            kEditText, dstEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_points);

        DBHandler db = new DBHandler(this, null);


        //NOT FILLING FOR SOME REASON!
        rosters = db.getRosterPlayers(teamName, week_num);

        teamName = TeamWeekActivity.team_name;
        team = (TextView) findViewById(R.id.teamTextView);
        team.setText(teamName);

        week_num = TeamWeekActivity.weekNum;


        weekNum = (TextView) findViewById(R.id.weekNumTextView);
        weekNum.setText(""+week_num);

        //set qbEditText var to equal the text in QBPts editText input
        qbEditText = (EditText) findViewById(R.id.QBPts);
        rbEditText = (EditText) findViewById(R.id.RBPts);
        wrEditText = (EditText) findViewById(R.id.WRPts);
        teEditText = (EditText) findViewById(R.id.TEPts);
        kEditText = (EditText) findViewById(R.id.KPts);
        dstEditText = (EditText) findViewById(R.id.DSTPts);




        qb = (TextView) findViewById(R.id.qbTextView);
        qb.setText(rosters[0].getPlayerName());
        qbEditText.setHint(rosters[0].getPts());


        rb = (TextView) findViewById(R.id.rbTextView);
        rb.setText(rosters[1].getPlayerName());
        rbEditText.setHint(rosters[1].getPts());


        wr = (TextView) findViewById(R.id.wrTextView);
        wr.setText(rosters[2].getPlayerName());
        wrEditText.setHint(rosters[2].getPts());



        te = (TextView) findViewById(R.id.teTextView);
        te.setText(rosters[3].getPlayerName());
        teEditText.setHint(rosters[3].getPts());


        k = (TextView) findViewById(R.id.kTextView);
        k.setText(rosters[4].getPlayerName());
        kEditText.setHint(rosters[4].getPts());


        dst = (TextView) findViewById(R.id.dstTextView);
        dst.setText(rosters[5].getPlayerName());
        dstEditText.setHint(rosters[5].getPts());


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

    //OnClick of submit button, get the user submitted points
    //for each of the 6 players and sum them, then display this sum on screen
    public void calcPoints(View view) {

        TextView total;

        //create int variables which will be totaled
        int qbPts, rbPts, wrPts, tePts, kPts, dstPts, total_pts;

        String qb = qbEditText.getText().toString();
        String rb = rbEditText.getText().toString();
        String wr = wrEditText.getText().toString();
        String te = teEditText.getText().toString();
        String k = kEditText.getText().toString();
        String dst = dstEditText.getText().toString();

        if (qb.trim().equals("") || rb.trim().equals("") || wr.trim().equals("") ||
                te.trim().equals("") || k.trim().equals("") || dst.trim().equals("")) {
            Toast.makeText(this, "Please enter the number of points scored Each Player!",
                    Toast.LENGTH_LONG).show();
        } else {
            //convert the string value in qb to integer, store it in int var qbPts
            qbPts = Integer.parseInt(qb);
            rbPts = Integer.parseInt(rb);
            wrPts = Integer.parseInt(wr);
            tePts = Integer.parseInt(te);
            kPts = Integer.parseInt(k);
            dstPts = Integer.parseInt(dst);

            //sum the integer values, store in int var total_pts
            total_pts = (qbPts + rbPts + wrPts + tePts + kPts + dstPts);
            //set TextView var total to equal totalTextView text box
            total = (TextView) findViewById(R.id.totalTextView);

            //code below ""+ takes the integer value from the int and converts it to
            //String so that it can be placed in the editText box
            //Does the same as String.valueOf(total_pts)
            total.setText("" + total_pts);
        }
    }

}
