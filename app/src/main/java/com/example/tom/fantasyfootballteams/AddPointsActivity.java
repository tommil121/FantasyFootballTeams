package com.example.tom.fantasyfootballteams;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AddPointsActivity extends AppCompatActivity {

    TextView team;
    String teamName;

    TextView weekNum;

    TextView qb;
    String qbName;

    TextView rb;
    String rbName;

    TextView wr;
    String wrName;

    TextView te;
    String teName;

    TextView k;
    String kName;

    TextView dst;
    String dstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_points);

        //fakeTeam eventually becomes return from roster table
        Team maury = new Team("Maury", "2015", "NFC");
        teamName = maury.getTeamName();
        team = (TextView) findViewById(R.id.teamTextView);
        team.setText(teamName);

        //fake week num
        weekNum = (TextView) findViewById(R.id.weekNumTextView);
        weekNum.setText("1");

        //added dummy PLAYER data to check ui
        Player qbPlayer = new Player("Maury Povich", "Maury", "QB");
        qbName = qbPlayer.getName();
        qb = (TextView) findViewById(R.id.qbTextView);
        qb.setText(qbName);

        Player rbPlayer = new Player("Jerry Springer", "Maury", "RB");
        rbName = rbPlayer.getName();
        rb = (TextView) findViewById(R.id.rbTextView);
        rb.setText(rbName);

        Player wrPlayer = new Player("Steve Wilkos", "Maury", "WR");
        wrName = wrPlayer.getName();
        wr = (TextView) findViewById(R.id.wrTextView);
        wr.setText(wrName);

        Player tePlayer = new Player("Montell Williams", "Maury", "TE");
        teName = tePlayer.getName();
        te = (TextView) findViewById(R.id.teTextView);
        te.setText(teName);

        Player kPlayer = new Player("Oprah", "Maury", "K");
        kName = kPlayer.getName();
        k = (TextView) findViewById(R.id.kTextView);
        k.setText(kName);

        Player dstPlayer = new Player("Ellen", "Maury", "DST");
        dstName = dstPlayer.getName();
        dst = (TextView) findViewById(R.id.dstTextView);
        dst.setText(dstName);

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

        //create TextView variables
        TextView qb, rb, wr, te, k, dst, total;

        //create int variables which will be totaled
        int qbPts, rbPts, wrPts, tePts, kPts, dstPts;
        //set int total_pts to 0
        int total_pts = 0;

        //set TextView var qb to equal the text in QBPts editText input
        qb = (TextView) findViewById(R.id.QBPts);
        //convert the string value in qb to integer, store it in int var qbPts
        qbPts = Integer.parseInt(qb.getText().toString());

        rb = (TextView) findViewById(R.id.RBPts);
        rbPts = Integer.parseInt(rb.getText().toString());

        wr = (TextView) findViewById(R.id.WRPts);
        wrPts = Integer.parseInt(wr.getText().toString());

        te = (TextView) findViewById(R.id.TEPts);
        tePts = Integer.parseInt(te.getText().toString());

        k = (TextView) findViewById(R.id.KPts);
        kPts = Integer.parseInt(k.getText().toString());

        dst = (TextView) findViewById(R.id.DSTPts);
        dstPts = Integer.parseInt(dst.getText().toString());

        //sum the integer values, store in int var total_pts
        total_pts = (qbPts + rbPts + wrPts + tePts + kPts + dstPts);
        //set TextView var total to equal totalTextView text box
        total = (TextView)findViewById(R.id.totalTextView);

        //code below ""+ takes the integer value from the int and converts it to
        //String so that it can be placed in the editText box
        //Does the same as String.valueOf(total_pts)
        total.setText(""+total_pts);

    }

}
