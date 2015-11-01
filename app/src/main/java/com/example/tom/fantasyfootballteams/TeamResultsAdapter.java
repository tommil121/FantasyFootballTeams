package com.example.tom.fantasyfootballteams;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Tom on 11/1/2015.
 */
public class TeamResultsAdapter extends ArrayAdapter<Team> {

    Team team;

    TextView teamNameTextView;
    TextView seasonTextView;
    TextView leagueNameTextView;

    public TeamResultsAdapter(Context context, Team[] objects) {
        super(context, R.layout.custom_team_row, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());

        View teamView = inflater.inflate(R.layout.custom_team_row, parent, false);

        team = getItem(position);

        teamNameTextView = (TextView) teamView.findViewById(R.id.teamNameTextView);
        seasonTextView = (TextView) teamView.findViewById(R.id.seasonTextView);
        leagueNameTextView = (TextView) teamView.findViewById(R.id.leagueNameTextView);

        teamNameTextView.setText(team.getTeamName());
        seasonTextView.setText(team.getSeason());
        leagueNameTextView.setText(team.getLeagueName());

        return teamView;
    }
}
