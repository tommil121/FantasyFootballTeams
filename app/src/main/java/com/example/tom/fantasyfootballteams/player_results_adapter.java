package com.example.tom.fantasyfootballteams;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Dustin on 11/2/15.
 */
public class player_results_adapter extends ArrayAdapter<Player> {
    //CREATE TEAM OBJECT
    Player player;

    //CREATE TEXTVIEW VARIABLES
    TextView playerNameTextView;
    TextView teamNameTextView;
    TextView positionTextView;

    //CONSTRUCTOR
    public player_results_adapter(Context context, Player[] objects) {
        super(context, R.layout.custom_player_row, objects);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //CREATE INFLATER OBJECT (PREPARES TEXT)
        LayoutInflater inflater = LayoutInflater.from(getContext());

        //CREATE TEAMVIEW OBJECT (LAYS OUT 1 CUSTOM ROW)
        View playerView = inflater.inflate(R.layout.custom_player_row, parent, false);

        player = getItem(position);

        //create references to what we need

        playerNameTextView = (TextView) playerView.findViewById(R.id.playerNameTextView);
        teamNameTextView = (TextView) playerView.findViewById(R.id.playerTeamNameTextView);
        positionTextView = (TextView) playerView.findViewById(R.id.positionTextView);

        //set references

        playerNameTextView.setText(player.getName());
        teamNameTextView.setText(player.getTeamName());
        positionTextView.setText(player.getPositionName());

        //retrn the player view
        return playerView;
    }
}
