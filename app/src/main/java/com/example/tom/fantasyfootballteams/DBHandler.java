package com.example.tom.fantasyfootballteams;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.sql.SQLClientInfoException;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tom on 10/31/2015.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATA_VERSION = 1;
    private static final String DATABASE_NAME = "team.db";

    private static final String TABLE_TEAM = "team";

    public static final String COLUMN_TEAMID = "id";
    public static final String COLUMN_TEAMNAME = "teamName";
    public static final String COLUMN_TEAMSEASON = "season";
    public static final String COLUMN_TEAMLEAGUENAME = "leagueName";

    //create player table
    public static final String TABLE_PLAYER = "PLAYER";
    public static final String COLUMN_PLAYER_ID = "PLAYER_ID";
    public static final String COLUMN_PLAYER_NAME = "PLAYER_NAME";
    public static final String COLUMN_PLAYER_NFL_TEAM = "NFL_TEAM";
    public static final String COLUMN_PLAYER_POSITION = "POSITION";
    public static final String COLUMN_PLAYER_TEAM_ID = "TEAM_ID";

    private Team [] teamData;
    private Player [] playerData;

    public DBHandler(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        //create team table
        String query = "CREATE TABLE " + TABLE_TEAM + "(" +
                COLUMN_TEAMID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TEAMNAME + " TEXT," +
                COLUMN_TEAMSEASON + " TEXT," +
                COLUMN_TEAMLEAGUENAME + " TEXT " +
                ");";

        db.execSQL(query);

        //create player table
        query = "CREATE TABLE " + TABLE_PLAYER + "(" +
                COLUMN_PLAYER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PLAYER_NAME + " TEXT NOT NULL, " +
                COLUMN_PLAYER_NFL_TEAM + " TEXT NOT NULL, " +
                COLUMN_PLAYER_POSITION + " TEXT NOT NULL, " +
                COLUMN_PLAYER_TEAM_ID + " INTEGER NOT NULL " +
                "FOREIGN KEY(PLAYER_TEAM_FK) REFERENCES TEAM(TEAM_ID)" +
                ");";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXIST " + TABLE_TEAM);
        onCreate(db);
    }

    //Add a row to the team table
    public void addTeam(String teamName, String season, String leagueName){

        //inserting column name and value
        ContentValues values = new ContentValues();

        values.put(COLUMN_TEAMNAME, teamName);
        values.put(COLUMN_TEAMSEASON, season);
        values.put(COLUMN_TEAMLEAGUENAME, leagueName);

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_TEAM, null, values);

        db.close();
    }

    public void deleteTeam(String teamName){

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_TEAM + " WHERE " + COLUMN_TEAMNAME + "=\"" + teamName + "\";");
    }
    public Team [] getTeams (){

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_TEAM + ";";

        Cursor c = db.rawQuery(query, null);

        int numTeams = c.getCount();

        if (numTeams >= 1) {

            teamData = new Team [numTeams];

            int i = 0;

            c.moveToFirst();

            while (!c.isAfterLast()){

                teamData[i] = new Team (c.getString(c.getColumnIndex("teamName")),
                        c.getString(c.getColumnIndex("season")),
                        c.getString(c.getColumnIndex("leagueName"))
                );

                c.moveToNext();

                i++;
            }
        }

        db.close();

        return teamData;
    }

    //Add a row to the player table
    public void addPlayer(Player player){
        ContentValues values = new ContentValues();

        //put the values into the values variable. Prepare to insert
        values.put(TABLE_PLAYER, player.getName());
        values.put(TABLE_PLAYER, player.getNflTeamName());
        values.put(TABLE_PLAYER, player.getPositionName());
        values.put(TABLE_PLAYER, player.getTeamId());


        //connects to the database (so you can write into it)
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PLAYER, null, values);
        db.close();
    }

    //Delete a row from the player table
    public void deletePlayer(String playerName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PLAYER + " WHERE " + COLUMN_PLAYER_NAME + "=\"" + playerName + "\";");

    }

    //retrieve rows from the player table
    public Player [] getPlayers (){

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_PLAYER + ";";

        Cursor c = db.rawQuery(query, null);

        int numPlayers = c.getCount();

        if (numPlayers >= 1) {

            playerData = new Player [numPlayers];

            int i = 0;

            c.moveToFirst();

            while (!c.isAfterLast()){

                playerData[i] = new Player (c.getString(c.getColumnIndex(COLUMN_PLAYER_NAME)),
                        c.getString(c.getColumnIndex(COLUMN_PLAYER_POSITION)),
                        c.getString(c.getColumnIndex(COLUMN_PLAYER_NFL_TEAM)),
                        c.getInt(c.getColumnIndex(COLUMN_PLAYER_TEAM_ID))

                );

                c.moveToNext();

                i++;
            }
        }

        db.close();

        return playerData;
    }

}
