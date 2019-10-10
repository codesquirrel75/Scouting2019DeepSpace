/*

 ***************  Code Designed by Team 107 Team Robotics *********************
 ***************  Edited for Team 1918 By Nate and Ken    *********************


 */

package com.example.vande.scouting2018;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.inputmethodservice.Keyboard;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vande.scouting2018.data.GetJasonData;
import com.example.vande.scouting2018.data.TeamsContract.TeamEntry;
import com.example.vande.scouting2018.data.TeamsDbHelper;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnKeyListener {


    public static TextView data;
    public static String TeamsList = "No teams loaded";
    public static String postData;

    SQLiteDatabase db;

    public ArrayList<String> teams = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // set up Database object
        TeamsDbHelper mDbHelper = new TeamsDbHelper(this);
        db = mDbHelper.getWritableDatabase();

        teams = TeamsDbHelper.getTeams(db);

        if (!teams.isEmpty()){
            TeamsList = "";
            for(int i =0; i < teams.size(); i++){

                TeamsList = TeamsList + teams.get(i) + "\n";
            }
        }

        data = findViewById(R.id.TeamsList);
        data.setText(TeamsList);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_activity:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.send_data:
                startActivity(new Intent(this, SendDataActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode != KeyEvent.KEYCODE_SPACE && keyCode != KeyEvent.KEYCODE_TAB) {
            TextInputEditText inputEditText = (TextInputEditText) v;

            if (inputEditText != null) {

                switch (inputEditText.getId()) {

                }
            }
        }
        return false;
    }

    public void showMatch(View view) {
        startActivity(new Intent(this, ScouterInitialsActivity.class));
    }

    public void showPit(View view) {
        startActivity(new Intent(this, PitActivity.class));
    }

    public void sendData(View view) {
        startActivity(new Intent(this, SendDataActivity.class));
    }

    public void getTeams(View view){

        TeamsDbHelper.dropTable(db);

        TeamsDbHelper.createTable(db);

        GetJasonData process = new GetJasonData(this);

        process.execute();



        Toast.makeText(this, "Import Complete", Toast.LENGTH_LONG).show();


    }
}
