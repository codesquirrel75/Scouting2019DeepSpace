package com.example.vande.scouting2018.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.vande.scouting2018.data.TeamsContract.TeamEntry;

import java.util.ArrayList;


public class TeamsDbHelper extends SQLiteOpenHelper {

    public static final String TAG = TeamsDbHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "teams.db";
    private static final int DATABASE_VERSION = 1;


    public TeamsDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d(TAG, "TeamsDbHelper: Database Created.");
    }



    @Override
    public void onCreate(SQLiteDatabase db) {

        createTable(db);

    }

    public static void createTable(SQLiteDatabase db){

        String sql_create_teams_table = "CREATE TABLE "+ TeamEntry.TABLE_NAME +"("
                + TeamEntry._ID +" Integer PRIMARY KEY AUTOINCREMENT, "
                + TeamEntry.COLUMN_TEAM_NUMBER +" text NOT NULL, "
                + TeamEntry.COLUMN_TEAM_NAME +" text);";

        db.execSQL(sql_create_teams_table);

    }

    public static void dropTable(SQLiteDatabase db){



        String sql_drop_table = "DROP TABLE if EXISTS " + TeamsContract.TeamEntry.TABLE_NAME + ";";


        db.execSQL(sql_drop_table);




    }


    public static void setTeams(SQLiteDatabase db, ContentValues values){


        long newIndex = db.insert(TeamsContract.TeamEntry.TABLE_NAME, null, values);

    }

    public static ArrayList<String> getTeamNumbers(SQLiteDatabase db){

        ArrayList<String> result = new ArrayList();

        Cursor cursor = db.rawQuery("SELECT "+ TeamEntry.COLUMN_TEAM_NUMBER + " FROM teams;",null);

        int numberColumn = cursor.getColumnIndex(TeamEntry.COLUMN_TEAM_NUMBER);

        cursor.moveToFirst();

        if(cursor != null && (cursor.getCount() > 0)){

            int i = 0;

            do {

                result.add(cursor.getString(numberColumn));

            }while (cursor.moveToNext());

        }

        return result;

    }

    public static ArrayList<String> getTeams(SQLiteDatabase db){

        ArrayList<String> result = new ArrayList();

        Cursor cursor = db.rawQuery("SELECT * FROM teams;",null);

        int numberColumn = cursor.getColumnIndex(TeamEntry.COLUMN_TEAM_NUMBER);
        int nameColumn = cursor.getColumnIndex(TeamEntry.COLUMN_TEAM_NAME);

        cursor.moveToFirst();

        if(cursor != null && (cursor.getCount() > 0)){

            int i = 0;

            do {

                result.add(cursor.getString(numberColumn) + "  |  " + cursor.getString(nameColumn));

            }while (cursor.moveToNext());

        }

        return result;

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


}