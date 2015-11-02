package com.example.tom.fantasyfootballteams;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ImageView;

/**
 * Created by Tom on 11/1/2015.
 */
public class TeamResultsAdapter extends ArrayAdapter<Team> {

    //CREATE TEAM OBJECT
    Team team;

    //CREATE TEXTVIEW VARIABLES
    TextView teamNameTextView;
    TextView seasonTextView;
    TextView leagueNameTextView;

    //CONSTRUCTOR
    public TeamResultsAdapter(Context context, Team[] objects) {
        super(context, R.layout.custom_team_row, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //CREATE INFLATER OBJECT (PREPARES TEXT)
        LayoutInflater inflater = LayoutInflater.from(getContext());

        //CREATE TEAMVIEW OBJECT (LAYS OUT 1 CUSTOM ROW)
        View teamView = inflater.inflate(R.layout.custom_team_row, parent, false);

        team = getItem(position);

        //create references to what we need
        ImageView teamImage = (ImageView) teamView.findViewById(R.id.teamImageView);
        teamNameTextView = (TextView) teamView.findViewById(R.id.teamNameTextView);
        seasonTextView = (TextView) teamView.findViewById(R.id.seasonTextView);
        leagueNameTextView = (TextView) teamView.findViewById(R.id.leagueNameTextView);

        //set references
        teamImage.setImageResource(R.drawable.maury);
        teamNameTextView.setText(team.getTeamName());
        seasonTextView.setText(team.getSeason());
        leagueNameTextView.setText(team.getLeagueName());

        //retrn the team view
        return teamView;
    }
}
