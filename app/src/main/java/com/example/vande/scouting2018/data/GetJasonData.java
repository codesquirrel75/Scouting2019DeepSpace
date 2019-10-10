package com.example.vande.scouting2018.data;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.vande.scouting2018.data.TeamsContract;
import com.example.vande.scouting2018.data.TeamsDbHelper;
import com.example.vande.scouting2018.data.TeamsContract.TeamEntry;

import android.os.AsyncTask;

import com.example.vande.scouting2018.MainActivity;
import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.vande.scouting2018.data.Settings.SettingsEntry;


public class GetJasonData extends AsyncTask<Void, Void, Void>{

    Context ctx;
    String data = "";
    String dataParsed = "";
    String singleParsed = "";
    String[] teams;


    public GetJasonData(Context ctx){

        this.ctx = ctx;

    }


    @Override
    protected Void doInBackground(Void... voids) {



        try {
            URL url = new URL(SettingsEntry.TEAMS_URL);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";

            while (line != null){

                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONObject JO = new JSONObject(data);
            JSONArray JA = JO.getJSONArray("valueRanges");
            JSONObject Jobj = new JSONObject(JA.getString(0));
            JSONArray Jarr = Jobj.getJSONArray("values");

            TeamsDbHelper mDbHelper = new TeamsDbHelper(ctx);
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();


            for(int i = 0; i < Jarr.length(); i++){


                String teamNumber = Jarr.getJSONArray(i).getString(0);
                String teamName = Jarr.getJSONArray(i).getString(1);
                singleParsed = singleParsed + teamNumber + "  |  " + teamName + "\n";
                values.put(TeamsContract.TeamEntry.COLUMN_TEAM_NUMBER, teamNumber);
                values.put(TeamsContract.TeamEntry.COLUMN_TEAM_NAME, teamName);
                TeamsDbHelper.setTeams(db, values);
            }



            db.close();




        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }



    @Override
    protected void onPostExecute(Void aVoid){
        super.onPostExecute(aVoid);


        MainActivity.data.setText(singleParsed);


    }


}