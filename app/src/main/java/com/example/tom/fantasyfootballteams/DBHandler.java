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

    private Team [] teamData;

    public DBHandler(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory, DATA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_TEAM + "(" +
                COLUMN_TEAMID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
                COLUMN_TEAMNAME + " TEXT " +
                COLUMN_TEAMSEASON + " TEXT " +
                COLUMN_TEAMLEAGUENAME + " TEXT " +
                ");";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXIST " + TABLE_TEAM);
        onCreate(db);
    }

    public void addPerson(String teamName, String season, String leagueName){

        //inserting column name and value
        ContentValues values = new ContentValues();

        values.put(COLUMN_TEAMNAME, teamName);
        values.put(COLUMN_TEAMSEASON, season);
        values.put(COLUMN_TEAMLEAGUENAME, leagueName);

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_TEAM, null, values);

        db.close();
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

}
