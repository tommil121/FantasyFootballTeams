package com.example.tom.fantasyfootballteams;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.sql.SQLClientInfoException;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Tom on 10/31/2015.
 */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATA_VERSION = 13;
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
    public static final String COLUMN_PLAYER_POSITION = "PLAYER_POSITION";
    public static final String COLUMN_PLAYER_TEAM_NAME = "TEAM_NAME"; //NOT CHANGING THIS YET... WILL CHANGE THOUGH
    public static final String COLUMN_PLAYER_BENCHED = "PLAYER_BENCHED";

    //create Roster table
    public static final String TABLE_ROSTER = "ROSTER";
    public static final String COLUMN_ROSTER_ID = "ROSTER_ID";
    public static final String COLUMN_ROSTER_PLAYER_NAME = "PLAYER_NAME";
    public static final String COLUMN_ROSTER_PLAYER_POSITION = "PLAYER_POSITION";
    public static final String COLUMN_ROSTER_PLAYER_TEAM_NAME = "TEAM_NAME";
    public static final String COLUMN_ROSTER_WEEK = "ROSTER_WEEK";
    public static final String COLUMN_ROSTER_PTS = "ROSTER_PTS";

    private Team [] teamData;
    private Player [] playerData;
    private Roster [] rosterData;

    public DBHandler(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        //create team table
        String query = "CREATE TABLE " + TABLE_TEAM + "(" +
                COLUMN_TEAMID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TEAMNAME + " TEXT, " +
                COLUMN_TEAMSEASON + " TEXT, " +
                COLUMN_TEAMLEAGUENAME + " TEXT " +
                ");";

        db.execSQL(query);

        //create player table
        query = "CREATE TABLE " + TABLE_PLAYER + "(" +
                COLUMN_PLAYER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_PLAYER_NAME + " TEXT, " +
                COLUMN_PLAYER_POSITION + " TEXT, " +
                COLUMN_PLAYER_TEAM_NAME + " TEXT, " +
                COLUMN_PLAYER_BENCHED + " INTEGER DEFAULT 1 " +
                ");"; //DEFAULT IS TRUE

        db.execSQL(query);

        //create Roster table
        query = "CREATE TABLE " + TABLE_ROSTER + "(" +
                COLUMN_ROSTER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_ROSTER_PLAYER_NAME + " TEXT, " +
                COLUMN_ROSTER_PLAYER_POSITION + " TEXT, " +
                COLUMN_ROSTER_PLAYER_TEAM_NAME + " TEXT, " +
                COLUMN_ROSTER_WEEK + " INTEGER, " +
                COLUMN_ROSTER_PTS + " INTEGER " +
                ");"; //pts will only include the whole number. If rounding is necessary, we will take care of that
                        //in the java of the add points page

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TEAM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROSTER);
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

    /*Dustin: I changed this because if the team name is not unique, it will cause problems
              because there could be teams with the same name, but different years. If we just
              solve this problem in the delete page, it will be okay. Also, if we use a dropdown
              on the delete team page, we will have to use an array of Teams, and we will already
              have the objects and the id
    */
    //deletes specified team row from database
    //based on team id
    //
    public void deleteTeam(Team team){



        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_TEAM + " WHERE "
                + COLUMN_TEAMID + "=" + team.getId() + ";");
    }

    public Team [] getTeamsByName(){

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_TEAM + " ORDER BY " + COLUMN_TEAMNAME + ";";

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

                teamData[i].setId(c.getInt(c.getColumnIndex("id")));

                c.moveToNext();

                i++;
            }
        }

        db.close();

        return teamData;
    }

    public Team [] getTeamsBySeason(){

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_TEAM + " ORDER BY " + COLUMN_TEAMSEASON + ";";

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

                teamData[i].setId(c.getInt(c.getColumnIndex("id")));

                c.moveToNext();

                i++;
            }
        }

        db.close();

        return teamData;
    }

    public Team [] getTeamsByLeague(){

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_TEAM + " ORDER BY " + COLUMN_TEAMLEAGUENAME + ";";

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

                teamData[i].setId(c.getInt(c.getColumnIndex("id")));

                c.moveToNext();

                i++;
            }
        }

        db.close();

        return teamData;
    }

    //Select teams with a where statement
    //Shouldn't need to be able to put any ints in because we are not using the PK as the FK, we are using the team_name
    public Team [] getTeamsWhere (String where, String operator, String condition, String andWhere,
                                  String andOp, String andCond ){

        SQLiteDatabase db = getWritableDatabase();

        String query = "";

        if(andWhere == null)
            query = "SELECT * FROM " + TABLE_TEAM + " WHERE " + where + operator + "\"" + condition + "\"" +  ";";
        else
            query = "SELECT * FROM " + TABLE_TEAM + " WHERE " + where + operator + "\"" + condition + "\"" +
                    " AND " + andWhere + andOp + "\"" + andCond + "\""  + ";";


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

                teamData[i].setId(c.getInt(c.getColumnIndex("id")));

                c.moveToNext();

                i++;
            }
        }

        db.close();

        return teamData;
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

                teamData[i].setId(c.getInt(c.getColumnIndex("id")));

                c.moveToNext();

                i++;
            }
        }

        db.close();

        return teamData;
    }

    //Add a row to the player table
    public void addPlayer(String playerName, String playerPosition, String playerTeam){
        ContentValues values = new ContentValues();

        //put the values into the values variable. Prepare to insert
        values.put(COLUMN_PLAYER_NAME, playerName);
        values.put(COLUMN_PLAYER_POSITION, playerPosition);
        values.put(COLUMN_PLAYER_TEAM_NAME, playerTeam);


        //connects to the database (so you can write into it)
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PLAYER, null, values);
        db.close();
    }

    //Delete a row from the player table
    public void deletePlayer(Player player){



        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PLAYER + " WHERE "
                + COLUMN_PLAYER_ID + "=" + player.getId() + ";");
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
                        c.getString(c.getColumnIndex(COLUMN_PLAYER_TEAM_NAME))

                );

                playerData[i].setId(c.getInt(c.getColumnIndex(COLUMN_PLAYER_ID)));

                c.moveToNext();

                i++;
            }
        }

        db.close();

        return playerData;
    }

    //use for long press
    public Player [] getPlayerWhereTeam(){
        return null;
    }

    public Player [] getPlayersByTeam (){

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_PLAYER + " ORDER BY " + COLUMN_PLAYER_TEAM_NAME + ";";

        Cursor c = db.rawQuery(query, null);

        int numPlayers = c.getCount();

        if (numPlayers >= 1) {

            playerData = new Player [numPlayers];

            int i = 0;

            c.moveToFirst();

            while (!c.isAfterLast()){

                playerData[i] = new Player (c.getString(c.getColumnIndex(COLUMN_PLAYER_NAME)),
                        c.getString(c.getColumnIndex(COLUMN_PLAYER_POSITION)),
                        c.getString(c.getColumnIndex(COLUMN_PLAYER_TEAM_NAME))

                );

                playerData[i].setId(c.getInt(c.getColumnIndex(COLUMN_PLAYER_ID)));

                c.moveToNext();

                i++;
            }
        }

        db.close();

        return playerData;
    }

    public Player [] getPlayersByName (){

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_PLAYER + " ORDER BY " + COLUMN_PLAYER_NAME + ";";

        Cursor c = db.rawQuery(query, null);

        int numPlayers = c.getCount();

        if (numPlayers >= 1) {

            playerData = new Player [numPlayers];

            int i = 0;

            c.moveToFirst();

            while (!c.isAfterLast()){

                playerData[i] = new Player (c.getString(c.getColumnIndex(COLUMN_PLAYER_NAME)),
                        c.getString(c.getColumnIndex(COLUMN_PLAYER_POSITION)),
                        c.getString(c.getColumnIndex(COLUMN_PLAYER_TEAM_NAME))

                );

                playerData[i].setId(c.getInt(c.getColumnIndex(COLUMN_PLAYER_ID)));

                c.moveToNext();

                i++;
            }
        }

        db.close();

        return playerData;
    }

    public Player [] getPlayersByPosition (){

        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_PLAYER + " ORDER BY " + COLUMN_PLAYER_POSITION + ";";

        Cursor c = db.rawQuery(query, null);

        int numPlayers = c.getCount();

        if (numPlayers >= 1) {

            playerData = new Player [numPlayers];

            int i = 0;

            c.moveToFirst();

            while (!c.isAfterLast()){

                playerData[i] = new Player (c.getString(c.getColumnIndex(COLUMN_PLAYER_NAME)),
                        c.getString(c.getColumnIndex(COLUMN_PLAYER_POSITION)),
                        c.getString(c.getColumnIndex(COLUMN_PLAYER_TEAM_NAME))

                );

                playerData[i].setId(c.getInt(c.getColumnIndex(COLUMN_PLAYER_ID)));

                c.moveToNext();

                i++;
            }
        }

        db.close();

        return playerData;
    }

    //retrieve a single row from the player table
    public Player getPlayer(String playerName, String team, int benched){
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_PLAYER +" WHERE " + COLUMN_PLAYER_NAME + " = \"" + playerName + "\" AND " +
                COLUMN_PLAYER_TEAM_NAME + "=\"" + team + "\" AND " + COLUMN_PLAYER_BENCHED + "= " + benched +";";

        Cursor c = db.rawQuery(query, null);

        int numPlayers = c.getCount();

        if (numPlayers >= 1) {

            playerData = new Player [numPlayers];

            int i = 0;

            c.moveToFirst();

            while (!c.isAfterLast()){

                playerData[i] = new Player (c.getString(c.getColumnIndex(COLUMN_PLAYER_NAME)),
                        c.getString(c.getColumnIndex(COLUMN_PLAYER_POSITION)),
                        c.getString(c.getColumnIndex(COLUMN_PLAYER_TEAM_NAME))

                );

                playerData[i].setId(c.getInt(c.getColumnIndex(COLUMN_PLAYER_ID)));

                c.moveToNext();

                i++;
            }
        }

        db.close();



        return playerData[0];
    }

    //retrieve rows from the player table
    public Player [] getPlayersWhere (String condition, String andWhere,
                                      String andOp, String andCond ){

        //clear the players
        playerData = null;

        SQLiteDatabase db = getWritableDatabase();

        String query = "";


        if(andWhere == null)
            query = "SELECT * FROM " + TABLE_PLAYER + " WHERE " + "TEAM_NAME=\"" + condition + "\";";
        else {
            query = "SELECT * FROM " + TABLE_PLAYER + " WHERE " + "TEAM_NAME=\"" + condition + "\"" +
                    " AND " + andWhere + andOp + "\"" + andCond + "\"" + ";";
        }

       Log.v("query", query);

        Cursor c = db.rawQuery(query, null);

        int numPlayers = c.getCount();

        if (numPlayers >= 1) {

            playerData = new Player [numPlayers];

            int i = 0;

            c.moveToFirst();

            while (!c.isAfterLast()){

                playerData[i] = new Player (c.getString(c.getColumnIndex(COLUMN_PLAYER_NAME)),
                        c.getString(c.getColumnIndex(COLUMN_PLAYER_POSITION)),
                        c.getString(c.getColumnIndex(COLUMN_PLAYER_TEAM_NAME))

                );

                playerData[i].setId(c.getInt(c.getColumnIndex(COLUMN_PLAYER_ID)));
                playerData[i].setBenched(c.getInt(c.getColumnIndex(COLUMN_PLAYER_BENCHED)));

                c.moveToNext();

                i++;
            }
        }

        db.close();
        c.close();

        return playerData;
    }

    public void updateBenched(Player player){
        SQLiteDatabase db = getWritableDatabase();

        String query;

        if(player.getBenched() == 0) {
            query = "UPDATE " + TABLE_PLAYER + " SET " + COLUMN_PLAYER_BENCHED + " = 1 WHERE "
                    + COLUMN_PLAYER_ID + " = " + player.getId();

            player.setBenched(1);
        } else {
            query = "UPDATE " + TABLE_PLAYER + " SET " + COLUMN_PLAYER_BENCHED + " = 0 WHERE "
                    + COLUMN_PLAYER_ID + " = " + player.getId();
            player.setBenched(0);
        }

       db.execSQL(query);

        db.close();


    }

    public void addToRoster(String playerName, String teamName, int week){

        //inserting column name and value
        ContentValues values = new ContentValues();

        Player player = getPlayer(playerName, teamName, 1);

        //getting the player name and week from the user
        values.put(COLUMN_ROSTER_PLAYER_NAME, playerName);
        values.put(COLUMN_ROSTER_PLAYER_POSITION, player.getTeamName()); //I DO NOT KNOW WHY, BUT THIS WORKS INSERTING TEAM NAME
        values.put(COLUMN_ROSTER_PLAYER_TEAM_NAME, player.getPositionName()); // INTO THE POSITION COLUMN AND VISA VERSA
        values.put(COLUMN_ROSTER_WEEK, week);

        //hardcoding zero in the pts column
        values.put(COLUMN_ROSTER_PTS, 0);

        SQLiteDatabase db = getWritableDatabase();



        Log.v("addR",  "" + db.insert(TABLE_ROSTER, null, values));

        db.close();

        updateBenched(player);

    }


    //get the Roster players for the add points page
    public Roster[] getRosterPosition(String teamName, int weekNum, String playerPos){
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_ROSTER +" WHERE " + COLUMN_ROSTER_PLAYER_TEAM_NAME + " = \"" + teamName + "\" AND " +
                COLUMN_ROSTER_WEEK + "=" + weekNum + " AND " + COLUMN_ROSTER_PLAYER_POSITION + " = \"" + playerPos +"\";";

        Cursor c = db.rawQuery(query, null);

        int numRosterPlayers = c.getCount();

        if (numRosterPlayers >= 1) {

            rosterData = new Roster [numRosterPlayers];

            int i = 0;

            c.moveToFirst();

            while (!c.isAfterLast()){

                rosterData[i] = new Roster (c.getString(c.getColumnIndex(COLUMN_ROSTER_PLAYER_NAME)),
                                c.getString(c.getColumnIndex(COLUMN_ROSTER_PLAYER_POSITION)),
                                c.getString(c.getColumnIndex(COLUMN_ROSTER_PLAYER_TEAM_NAME)),
                                c.getInt(c.getColumnIndex(COLUMN_ROSTER_WEEK)),
                                c.getInt(c.getColumnIndex(COLUMN_ROSTER_PTS))

                );

                rosterData[i].setRosterId(c.getInt(c.getColumnIndex(COLUMN_ROSTER_ID)));

                c.moveToNext();

                i++;
            }
        }

        db.close();

        return rosterData;


    }

    public Roster[] getRosterPlayers(String teamName, int weekNum){
        SQLiteDatabase db = getWritableDatabase();

        String query = "SELECT * FROM " + TABLE_ROSTER + " WHERE " + COLUMN_ROSTER_PLAYER_TEAM_NAME + " = \"" + teamName.trim() + "\" AND " +
                COLUMN_ROSTER_WEEK + "=" + weekNum +";";

        Log.v("rosterSelect", "" + db.rawQuery(query, null));

        Cursor c = db.rawQuery(query, null);

        int numRosterPlayers = c.getCount();

        if (numRosterPlayers >= 1) {

            rosterData = new Roster [numRosterPlayers];

            int i = 0;

            c.moveToFirst();

            while (!c.isAfterLast()){

                rosterData[i] = new Roster (c.getString(c.getColumnIndex(COLUMN_ROSTER_PLAYER_NAME)),
                        c.getString(c.getColumnIndex(COLUMN_ROSTER_PLAYER_POSITION)),
                        c.getString(c.getColumnIndex(COLUMN_ROSTER_PLAYER_TEAM_NAME)),
                        c.getInt(c.getColumnIndex(COLUMN_ROSTER_WEEK)),
                        c.getInt(c.getColumnIndex(COLUMN_ROSTER_PTS))

                );

                rosterData[i].setRosterId(c.getInt(c.getColumnIndex(COLUMN_ROSTER_ID)));

                c.moveToNext();

                i++;
            }
        }

        db.close();



        return rosterData;


    }

    public void updatePts(String position, String teamName, int weekNum, int pts){
        SQLiteDatabase db = getWritableDatabase();

        String query = "UPDATE " + TABLE_ROSTER + " SET " + COLUMN_ROSTER_PTS + " = " + pts + " WHERE "
                + COLUMN_ROSTER_PLAYER_POSITION + "= \"" + position + "\" AND " + COLUMN_ROSTER_PLAYER_TEAM_NAME
                + "= \"" + teamName + "\" AND " + COLUMN_ROSTER_WEEK + " = " + weekNum + ";";

        Log.v("ptsUpdate", query);

        db.execSQL(query);

        db.close();


    }

}
