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
        team = (TextView)findViewById(R.id.teamTextView);
        team.setText(teamName);

        //added dummy data to check ui
        Player qbPlayer = new Player("Maury Povich", "Maury", "QB");
        qbName = qbPlayer.getName();
        qb = (TextView)findViewById(R.id.qbTextView);
        qb.setText(qbName);

        Player rbPlayer = new Player("Jerry Springer", "Maury", "RB");
        rbName = rbPlayer.getName();
        rb = (TextView)findViewById(R.id.rbTextView);
        rb.setText(rbName);

        Player wrPlayer = new Player("Steve Wilkos", "Maury", "WR");
        wrName = wrPlayer.getName();
        wr = (TextView) findViewById(R.id.wrTextView);
        wr.setText(wrName);

        Player tePlayer = new Player("Montell Williams", "Maury", "TE");
        teName = tePlayer.getName();
        te = (TextView)findViewById(R.id.teTextView);
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

}
