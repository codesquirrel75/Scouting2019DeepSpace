/*

***************  Code Designed by Team 107 Team Robotics *********************
***************  Edited for Team 1918 By Nate and Ken    *********************


 */

package com.example.vande.scouting2018;

import android.Manifest;
import android.graphics.Color;
import android.os.Environment;

import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.design.widget.TextInputLayout;
import android.content.Intent;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import utils.FormatStringUtils;
import utils.PermissionUtils;
import utils.StringUtils;
import utils.ViewUtils;

import static android.R.attr.value;
import static com.example.vande.scouting2018.AutonActivity.AUTON_STRING_EXTRA;
import static com.example.vande.scouting2018.AutonActivity.MATCH_STRING_EXTRA;
import static com.example.vande.scouting2018.AutonActivity.TEAMNUMBER_STRING_EXTRA;


public class TeleopActivity extends AppCompatActivity implements View.OnKeyListener {
    /*This area sets and binds all of the variables that we will use in the auton activity*/

    @BindView(R.id.teleop_cargo_ship_hatch_panel_input_layout)
    public TextInputLayout teleopCargoShipHatchPanelInputLayout;

    @BindView(R.id.teleop_cargo_in_cargo_ship_input_layout)
    public TextInputLayout teleopCargoInCargoShipInputLayout;

    @BindView(R.id.teleop_hatch_panel_top_input_layout)
    public TextInputLayout teleopHatchPanelTopInputLayout;

    @BindView(R.id.teleop_hatch_panel_middle_input_layout)
    public TextInputLayout teleopHatchPanelMiddleInputLayout;

    @BindView(R.id.teleop_hatch_panel_bottom_input_layout)
    public TextInputLayout teleopHatchPanelBottomInputLayout;

    @BindView(R.id.teleop_cargo_top_input_layout)
    public TextInputLayout teleopCargoTopInputLayout;

    @BindView(R.id.teleop_cargo_middle_input_layout)
    public TextInputLayout teleopCargoMiddleInputLayout;

    @BindView(R.id.teleop_cargo_bottom_input_layout)
    public TextInputLayout teleopCargoBottomInputLayout;

    @BindView(R.id.teleop_cargo_ship_hatch_panel_input)
    public TextInputEditText teleopCargoShipHatchPanelInput;

    @BindView(R.id.teleop_cargo_in_cargo_ship_input)
    public TextInputEditText teleopCargoInCargoShipInput;

    @BindView(R.id.teleop_hatch_panel_top_input)
    public TextInputEditText teleopHatchPanelTopInput;

    @BindView(R.id.teleop_hatch_panel_middle_input)
    public TextInputEditText teleopHatchPanelMiddleInput;

    @BindView(R.id.teleop_hatch_panel_bottom_input)
    public TextInputEditText teleopHatchPanelBottomInput;

    @BindView(R.id.teleop_cargo_top_input)
    public TextInputEditText teleopCargoTopInput;

    @BindView(R.id.teleop_cargo_middle_input)
    public TextInputEditText teleopCargoMiddleInput;

    @BindView(R.id.teleop_cargo_bottom_input)
    public TextInputEditText teleopCargoBottomInput;

    @BindView(R.id.hatch_panel_pickup)
    public RadioGroup HatchPanelPickup;

    @BindView(R.id.cargo_pickup)
    public RadioGroup CargoPickup;

    @BindView(R.id.defense_effectiveness)
    public RadioGroup defenseEffectiveness;

    @BindView(R.id.end_game_location_spinner)
    public Spinner endGameLocationSpinner;

    @BindView(R.id.cycle_time_spinner)
    public Spinner cycleTimeSpinner;

    @BindView(R.id.overall_effectiveness_radio_group)
    public RadioGroup overallEffectivenessRadioGoup;

    @BindView(R.id.overall_hatch_cargo_placement_radio_group)
    public RadioGroup overallPlacementRadioGoup;

    @BindView(R.id.trained_drive_team_radio_group)
    public RadioGroup trainedDriveTeamRadioGoup;

    @BindView(R.id.observ_cargo_pickup)
    public CheckBox observCargoPickup;

    @BindView(R.id.observ_died_back)
    public CheckBox observDiedBack;

    @BindView(R.id.observ_died_mid)
    public CheckBox observDiedMid;

    @BindView(R.id.observ_dns)
    public CheckBox observDns;

    @BindView(R.id.observ_fast)
    public CheckBox observFast;

    @BindView(R.id.observ_fell_apart)
    public CheckBox observFellApart;

    @BindView(R.id.observ_fell_over)
    public CheckBox observFellOver;

    @BindView(R.id.observ_hatch_pickup)
    public CheckBox observHatchPickup;

    @BindView(R.id.observ_jerky)
    public CheckBox observJerky;

    @BindView(R.id.observ_not_much)
    public CheckBox observNotMuch;

    @BindView(R.id.observ_penalties)
    public CheckBox observPenalties;

    @BindView(R.id.observ_played_defense)
    public CheckBox observPlayedDefense;

    @BindView(R.id.observ_slow)
    public CheckBox observSlow;

    @BindView(R.id.observ_slowed_by_robot)
    public CheckBox observSlowedByRobot;

    @BindView(R.id.observ_smooth)
    public CheckBox observsmooth;

    @BindView(R.id.observ_average_speed)
    public CheckBox observAverageSpeed;

    @BindView(R.id.observ_dropped_hatches)
    public CheckBox observDroppedHatches;

    @BindView(R.id.observ_dropped_cargo)
    public CheckBox observDroppedCargo;

    @BindView(R.id.observ_hard_time_hatches)
    public CheckBox observHardTimeHatches;

    @BindView(R.id.observ_hard_time_cargo)
    public  CheckBox observHardTimeCargo;

    @BindView(R.id.summary_input)
    public EditText summaryInput;

    @BindView(R.id.issues_input)
    public EditText issuesInput;

    public String observations = "";

    @BindView(R.id.save_btn)
    public Button saveBtn;

    int teleopCargoShipHatchPanel = 0;
    int teleopCargoShipCargo = 0;
    int teleopHatchPanelTop = 0;
    int teleopHatchPanelMiddle = 0;
    int teleopHatchPanelBottom =0;
    int teleopCargotop = 0;
    int teleopCargoMiddle = 0;
    int teleopCargoBottom = 0;





    public String auton;
    public String matchNumber;
    public String teamNumber;

    private ArrayList<CharSequence> teleopDataStringList;
/*
 *When this activity is first called,
 *we will call the activity_auton layout so we can display
 *the user interface
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teleop);

        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        auton = bundle.getString(AUTON_STRING_EXTRA);
        matchNumber = bundle.getString(MATCH_STRING_EXTRA);
        teamNumber = bundle.getString(TEAMNUMBER_STRING_EXTRA);

        getSupportActionBar().setTitle("Match: " + matchNumber + " - Team: " + teamNumber);

        teleopDataStringList = new ArrayList<>();

        displayTeleopCargoShipHatchPanelInput(teleopCargoShipHatchPanel);
        displayTeleopCargoShipCargoInput(teleopCargoShipCargo);
        displayTeleopHatchPanelTopInput(teleopHatchPanelTop);
        displayTeleopHatchPanelMiddleInput(teleopHatchPanelMiddle);
        displayTeleopHatchPanelBottomInput(teleopHatchPanelBottom);
        displayTeleopCargoTopInput(teleopCargotop);
        displayTeleopCargoMiddleInput(teleopCargoMiddle);
        displayTeleopCargoBottomInput(teleopCargoBottom);


        //  --- End Game Location spinner ---

        Spinner endgamelocationspinner = (Spinner) findViewById(R.id.end_game_location_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> endgamelocationadapter = ArrayAdapter.createFromResource(this,
                R.array.endgame_location, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        endgamelocationadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        endgamelocationspinner.setAdapter(endgamelocationadapter);


        //  --- Cycle Time spinner ---

        Spinner cycletimespinner = (Spinner) findViewById(R.id.cycle_time_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> cycletimeadapter = ArrayAdapter.createFromResource(this,
                R.array.cycle_time_spinner, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        cycletimeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        cycletimespinner.setAdapter(cycletimeadapter);

    }


    /*If this activity is resumed from a paused state the data
     *will be set to what they previously were set to
     */
    @Override
    protected void onResume() {
        super.onResume();

        teleopCargoShipHatchPanelInput.setOnKeyListener(this);
        teleopCargoInCargoShipInput.setOnKeyListener(this);
        teleopHatchPanelTopInput.setOnKeyListener(this);
        teleopHatchPanelMiddleInput.setOnKeyListener(this);
        teleopHatchPanelBottomInput.setOnKeyListener(this);
        teleopCargoTopInput.setOnKeyListener(this);
        teleopCargoTopInput.setOnKeyListener(this);
        teleopCargoTopInput.setOnKeyListener(this);
        defenseEffectiveness.setOnKeyListener(this);
        endGameLocationSpinner.setOnKeyListener(this);
        cycleTimeSpinner.setOnKeyListener(this);
        overallEffectivenessRadioGoup.setOnKeyListener(this);
        HatchPanelPickup.setOnKeyListener(this);
        CargoPickup.setOnKeyListener(this);
        overallPlacementRadioGoup.setOnKeyListener(this);
        trainedDriveTeamRadioGoup.setOnKeyListener(this);
        observsmooth.setOnKeyListener(this);
        observSlowedByRobot.setOnKeyListener(this);
        observSlow.setOnKeyListener(this);
        observPlayedDefense.setOnKeyListener(this);
        observPenalties.setOnKeyListener(this);
        observNotMuch.setOnKeyListener(this);
        observJerky.setOnKeyListener(this);
        observHatchPickup.setOnKeyListener(this);
        observFellOver.setOnKeyListener(this);
        observFellApart.setOnKeyListener(this);
        observFast.setOnKeyListener(this);
        observDns.setOnKeyListener(this);
        observDiedMid.setOnKeyListener(this);
        observDiedBack.setOnKeyListener(this);
        observCargoPickup.setOnKeyListener(this);
        observAverageSpeed.setOnKeyListener(this);
        observDroppedHatches.setOnKeyListener(this);
        observDroppedCargo.setOnKeyListener(this);
        observHardTimeHatches.setOnKeyListener(this);
        observHardTimeCargo.setOnKeyListener(this);
        summaryInput.setOnKeyListener(this);
        issuesInput.setOnKeyListener(this);

    }

    /*If this activity enters a paused state the data will be set to null*/
    @Override
    protected void onPause() {
        super.onPause();

        teleopCargoShipHatchPanelInput.setOnKeyListener(null);
        teleopCargoInCargoShipInput.setOnKeyListener(null);
        teleopHatchPanelTopInput.setOnKeyListener(null);
        teleopHatchPanelMiddleInput.setOnKeyListener(null);
        teleopHatchPanelBottomInput.setOnKeyListener(null);
        teleopCargoTopInput.setOnKeyListener(null);
        teleopCargoTopInput.setOnKeyListener(null);
        teleopCargoTopInput.setOnKeyListener(null);
        defenseEffectiveness.setOnKeyListener(null);
        endGameLocationSpinner.setOnKeyListener(null);
        cycleTimeSpinner.setOnKeyListener(null);
        overallEffectivenessRadioGoup.setOnKeyListener(null);
        HatchPanelPickup.setOnKeyListener(null);
        CargoPickup.setOnKeyListener(null);
        overallPlacementRadioGoup.setOnKeyListener(null);
        trainedDriveTeamRadioGoup.setOnKeyListener(null);
        observsmooth.setOnKeyListener(null);
        observSlowedByRobot.setOnKeyListener(null);
        observSlow.setOnKeyListener(null);
        observPlayedDefense.setOnKeyListener(null);
        observPenalties.setOnKeyListener(null);
        observNotMuch.setOnKeyListener(null);
        observJerky.setOnKeyListener(null);
        observHatchPickup.setOnKeyListener(null);
        observFellOver.setOnKeyListener(null);
        observFellApart.setOnKeyListener(null);
        observFast.setOnKeyListener(null);
        observDns.setOnKeyListener(null);
        observDiedMid.setOnKeyListener(null);
        observDiedBack.setOnKeyListener(null);
        observCargoPickup.setOnKeyListener(null);
        observAverageSpeed.setOnKeyListener(null);
        observDroppedHatches.setOnKeyListener(null);
        observDroppedCargo.setOnKeyListener(null);
        observHardTimeHatches.setOnKeyListener(null);
        observHardTimeCargo.setOnKeyListener(null);
        summaryInput.setOnKeyListener(null);
        issuesInput.setOnKeyListener(null);

    }

    /* This method will display the options menu when the icon is pressed
     * and this will inflate the menu options for the user to choose
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    /*This method will launch the correct activity
     *based on the menu option user presses
      */
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

    private void setSpinnerError(Spinner spinner, String error){
        View selectedView = spinner.getSelectedView();
        if (selectedView instanceof TextView){
            spinner.requestFocus();
            TextView selectedTextView = (TextView) selectedView;
            selectedTextView.setError("error");
            selectedTextView.setTextColor(Color.RED);
            selectedTextView.setText(error);

        }
    }

    //Teleop Cargo Ship Hatch Panels
    public void decreaseCargoShipHatchPanelInput(View view) {
        if (teleopCargoShipHatchPanel != 0) {
            teleopCargoShipHatchPanel = teleopCargoShipHatchPanel - 1;
            displayTeleopCargoShipHatchPanelInput(teleopCargoShipHatchPanel);
        }
    }

    public void increaseCargoShipHatchPanelInput(View view) {
        if (teleopCargoShipHatchPanel <= 7) {
            teleopCargoShipHatchPanel = teleopCargoShipHatchPanel + 1;
            displayTeleopCargoShipHatchPanelInput(teleopCargoShipHatchPanel);
        }
    }

    private void displayTeleopCargoShipHatchPanelInput(int number) {
        teleopCargoShipHatchPanelInput.setText("" + number);
    }

    //Teleop Cargo Ship Cargo
    public void decreaseCargoInCargoShipInput(View view) {
        if (teleopCargoShipCargo != 0) {
            teleopCargoShipCargo = teleopCargoShipCargo - 1;
            displayTeleopCargoShipCargoInput(teleopCargoShipCargo);
        }
    }

    public void increaseCargoInCargoShipInput(View view) {
        if (teleopCargoShipCargo <= 7) {
            teleopCargoShipCargo = teleopCargoShipCargo + 1;
            displayTeleopCargoShipCargoInput(teleopCargoShipCargo);
        }
    }

    private void displayTeleopCargoShipCargoInput(int number) {
        teleopCargoInCargoShipInput.setText("" + number);
    }

    //Teleop Rocket Ship Hatch Panels

    public void decreaseHatchPanelTopInput(View view) {
        if (teleopHatchPanelTop != 0) {
            teleopHatchPanelTop = teleopHatchPanelTop - 1;
            displayTeleopHatchPanelTopInput(teleopHatchPanelTop);
        }
    }

    public void increaseHatchPanelTopInput(View view) {
        if (teleopHatchPanelTop <= 3) {
            teleopHatchPanelTop = teleopHatchPanelTop + 1;
            displayTeleopHatchPanelTopInput(teleopHatchPanelTop);
        }
    }

    private void displayTeleopHatchPanelTopInput(int number) {
        teleopHatchPanelTopInput.setText("" + number);
    }

        public void decreaseHatchPanelMiddleInput(View view) {
            if (teleopHatchPanelMiddle != 0) {
                teleopHatchPanelMiddle = teleopHatchPanelMiddle - 1;
                displayTeleopHatchPanelMiddleInput(teleopHatchPanelMiddle);
            }
        }

        public void increaseHatchPanelMiddleInput(View view) {
            if (teleopHatchPanelMiddle <= 3) {
                teleopHatchPanelMiddle = teleopHatchPanelMiddle + 1;
                displayTeleopHatchPanelMiddleInput(teleopHatchPanelMiddle);
            }
        }

        private void displayTeleopHatchPanelMiddleInput(int number) {
            teleopHatchPanelMiddleInput.setText("" + number);
        }

            public void decreaseHatchPanelBottomInput(View view) {
                if (teleopHatchPanelBottom != 0) {
                    teleopHatchPanelBottom = teleopHatchPanelBottom - 1;
                    displayTeleopHatchPanelBottomInput(teleopHatchPanelBottom);
                }
            }

            public void increaseHatchPanelBottomInput(View view) {
                if (teleopHatchPanelBottom <= 3) {
                    teleopHatchPanelBottom = teleopHatchPanelBottom + 1;
                    displayTeleopHatchPanelBottomInput(teleopHatchPanelBottom);
                }
            }

            private void displayTeleopHatchPanelBottomInput(int number) {
                teleopHatchPanelBottomInput.setText("" + number);
            }


    //Teleop Rocket Ship Cargo

    public void decreaseCargoTopInput(View view) {
        if (teleopCargotop != 0) {
            teleopCargotop = teleopCargotop - 1;
            displayTeleopCargoTopInput(teleopCargotop);
        }
    }

    public void increaseCargoTopInput(View view) {
        if (teleopCargotop <= 3) {
            teleopCargotop = teleopCargotop + 1;
            displayTeleopCargoTopInput(teleopCargotop);
        }
    }

    private void displayTeleopCargoTopInput(int number) {
        teleopCargoTopInput.setText("" + number);
    }

    public void decreaseCargoMiddleInput(View view) {
        if (teleopCargoMiddle != 0) {
            teleopCargoMiddle = teleopCargoMiddle - 1;
            displayTeleopCargoMiddleInput(teleopCargoMiddle);
        }
    }

    public void increaseCargoMiddleInput(View view) {
        if (teleopCargoMiddle <= 3) {
            teleopCargoMiddle = teleopCargoMiddle + 1;
            displayTeleopCargoMiddleInput(teleopCargoMiddle);
        }
    }

    private void displayTeleopCargoMiddleInput(int number) {
        teleopCargoMiddleInput.setText("" + number);
    }

    public void decreaseCargoBottomInput(View view) {
        if (teleopCargoBottom != 0) {
            teleopCargoBottom = teleopCargoBottom - 1;
            displayTeleopCargoBottomInput(teleopCargoBottom);
        }
    }

    public void increaseCargoBottomInput(View view) {
        if (teleopCargoBottom <= 3) {
            teleopCargoBottom = teleopCargoBottom + 1;
            displayTeleopCargoBottomInput(teleopCargoBottom);
        }
    }

    private void displayTeleopCargoBottomInput(int number) {
        teleopCargoBottomInput.setText("" + number);
    }



    // this method sets up a string for a group of checkBoxes

    public void setString(View view) {
        Boolean checked = ((CheckBox) view).isChecked();
        String s1;

        switch (view.getId()) {
            case R.id.observ_cargo_pickup:
                s1 = observCargoPickup.getText().toString() + " |";
                if (checked) {
                    if (observations.isEmpty()) {
                        observations = s1;
                    } else {
                        observations = observations + s1;
                    }
                } else {
                    if (observations.contains(s1)) {
                        int start = observations.indexOf(s1);
                        observations = observations.substring(0, start) + observations.substring(start + s1.length());
                    }
                }
                break;
            case R.id.observ_died_back:
                s1 = observDiedBack.getText().toString() + " |";
                if (checked) {
                    if (observations.isEmpty()) {
                        observations = s1;
                    } else {
                        observations = observations + s1;
                    }
                } else {
                    if (observations.contains(s1)) {
                        int start = observations.indexOf(s1);
                        observations = observations.substring(0, start) + observations.substring(start + s1.length());
                    }
                }
                break;
            case R.id.observ_died_mid:
                s1 = observDiedMid.getText().toString() + " |";
                if (checked) {
                    if (observations.isEmpty()) {
                        observations = s1;
                    } else {
                        observations = observations + s1;
                    }
                } else {
                    if (observations.contains(s1)) {
                        int start = observations.indexOf(s1);
                        observations = observations.substring(0, start) + observations.substring(start + s1.length());
                    }
                }
                break;
            case R.id.observ_dns:
                s1 = observDns.getText().toString() + " |";
                if (checked) {
                    if (observations.isEmpty()) {
                        observations = s1;
                    } else {
                        observations = observations + s1;
                    }
                } else {
                    if (observations.contains(s1)) {
                        int start = observations.indexOf(s1);
                        observations = observations.substring(0, start) + observations.substring(start + s1.length());
                    }
                }
                break;
            case R.id.observ_fast:
                s1 = observFast.getText().toString() + " |";
                if (checked) {
                    if (observations.isEmpty()) {
                        observations = s1;
                    } else {
                        observations = observations + s1;
                    }
                } else {
                    if (observations.contains(s1)) {
                        int start = observations.indexOf(s1);
                        observations = observations.substring(0, start) + observations.substring(start + s1.length());
                    }
                }
                break;
            case R.id.observ_fell_apart:
                s1 = observFellApart.getText().toString() + " |";
                if (checked) {
                    if (observations.isEmpty()) {
                        observations = s1;
                    } else {
                        observations = observations + s1;
                    }
                } else {
                    if (observations.contains(s1)) {
                        int start = observations.indexOf(s1);
                        observations = observations.substring(0, start) + observations.substring(start + s1.length());
                    }
                }
                break;
            case R.id.observ_fell_over:
                s1 = observFellOver.getText().toString() + " |";
                if (checked) {
                    if (observations.isEmpty()) {
                        observations = s1;
                    } else {
                        observations = observations + s1;
                    }
                } else {
                    if (observations.contains(s1)) {
                        int start = observations.indexOf(s1);
                        observations = observations.substring(0, start) + observations.substring(start + s1.length());
                    }
                }
                break;
            case R.id.observ_hatch_pickup:
                s1 = observHatchPickup.getText().toString() + " |";
                if (checked) {
                    if (observations.isEmpty()) {
                        observations = s1;
                    } else {
                        observations = observations + s1;
                    }
                } else {
                    if (observations.contains(s1)) {
                        int start = observations.indexOf(s1);
                        observations = observations.substring(0, start) + observations.substring(start + s1.length());
                    }
                }
                break;
            case R.id.observ_jerky:
                s1 = observJerky.getText().toString() + " |";
                if (checked) {
                    if (observations.isEmpty()) {
                        observations = s1;
                    } else {
                        observations = observations + s1;
                    }
                } else {
                    if (observations.contains(s1)) {
                        int start = observations.indexOf(s1);
                        observations = observations.substring(0, start) + observations.substring(start + s1.length());
                    }
                }
                break;
            case R.id.observ_not_much:
                s1 = observNotMuch.getText().toString() + " |";
                if (checked) {
                    if (observations.isEmpty()) {
                        observations = s1;
                    } else {
                        observations = observations + s1;
                    }
                } else {
                    if (observations.contains(s1)) {
                        int start = observations.indexOf(s1);
                        observations = observations.substring(0, start) + observations.substring(start + s1.length());
                    }
                }
                break;
            case R.id.observ_penalties:
                s1 = observPenalties.getText().toString() + " |";
                if (checked) {
                    if (observations.isEmpty()) {
                        observations = s1;
                    } else {
                        observations = observations + s1;
                    }
                } else {
                    if (observations.contains(s1)) {
                        int start = observations.indexOf(s1);
                        observations = observations.substring(0, start) + observations.substring(start + s1.length());
                    }
                }
                break;
            case R.id.observ_played_defense:
                s1 = observPlayedDefense.getText().toString() + " |";
                if (checked) {
                    if (observations.isEmpty()) {
                        observations = s1;
                    } else {
                        observations = observations + s1;
                    }
                } else {
                    if (observations.contains(s1)) {
                        int start = observations.indexOf(s1);
                        observations = observations.substring(0, start) + observations.substring(start + s1.length());
                    }
                }
                break;
            case R.id.observ_slow:
                s1 = observSlow.getText().toString() + " |";
                if (checked) {
                    if (observations.isEmpty()) {
                        observations = s1;
                    } else {
                        observations = observations + s1;
                    }
                } else {
                    if (observations.contains(s1)) {
                        int start = observations.indexOf(s1);
                        observations = observations.substring(0, start) + observations.substring(start + s1.length());
                    }
                }
                break;
            case R.id.observ_slowed_by_robot:
                s1 = observSlowedByRobot.getText().toString() + " |";
                if (checked) {
                    if (observations.isEmpty()) {
                        observations = s1;
                    } else {
                        observations = observations + s1;
                    }
                } else {
                    if (observations.contains(s1)) {
                        int start = observations.indexOf(s1);
                        observations = observations.substring(0, start) + observations.substring(start + s1.length());
                    }
                }
                break;
            case R.id.observ_smooth:
                s1 = observsmooth.getText().toString() + " |";
                if (checked) {
                    if (observations.isEmpty()) {
                        observations = s1;
                    } else {
                        observations = observations + s1;
                    }
                } else {
                    if (observations.contains(s1)) {
                        int start = observations.indexOf(s1);
                        observations = observations.substring(0, start) + observations.substring(start + s1.length());
                    }
                }
                break;
            case R.id.observ_average_speed:
                s1 = observAverageSpeed.getText().toString() + " |";
                if (checked) {
                    if (observations.isEmpty()) {
                        observations = s1;
                    } else {
                        observations = observations + s1;
                    }
                } else {
                    if (observations.contains(s1)) {
                        int start = observations.indexOf(s1);
                        observations = observations.substring(0, start) + observations.substring(start + s1.length());
                    }
                }
                break;
            case R.id.observ_dropped_hatches:
                s1 = observDroppedHatches.getText().toString() + " |";
                if (checked) {
                    if (observations.isEmpty()) {
                        observations = s1;
                    } else {
                        observations = observations + s1;
                    }
                } else {
                    if (observations.contains(s1)) {
                        int start = observations.indexOf(s1);
                        observations = observations.substring(0, start) + observations.substring(start + s1.length());
                    }
                }
                break;
            case R.id.observ_dropped_cargo:
                s1 = observDroppedCargo.getText().toString() + " |";
                if (checked) {
                    if (observations.isEmpty()) {
                        observations = s1;
                    } else {
                        observations = observations + s1;
                    }
                } else {
                    if (observations.contains(s1)) {
                        int start = observations.indexOf(s1);
                        observations = observations.substring(0, start) + observations.substring(start + s1.length());
                    }
                }
                break;
            case R.id.observ_hard_time_hatches:
                s1 = observHardTimeHatches.getText().toString() + " |";
                if (checked) {
                    if (observations.isEmpty()) {
                        observations = s1;
                    } else {
                        observations = observations + s1;
                    }
                } else {
                    if (observations.contains(s1)) {
                        int start = observations.indexOf(s1);
                        observations = observations.substring(0, start) + observations.substring(start + s1.length());
                    }
                }
                break;
            case R.id.observ_hard_time_cargo:
                s1 = observHardTimeCargo.getText().toString() + " |";
                if (checked) {
                    if (observations.isEmpty()) {
                        observations = s1;
                    } else {
                        observations = observations + s1;
                    }
                } else {
                    if (observations.contains(s1)) {
                        int start = observations.indexOf(s1);
                        observations = observations.substring(0, start) + observations.substring(start + s1.length());
                    }
                }
                break;
        }
    }


    /*This method will look at all of the text/number input fields and set error
    *for validation of data entry
     */
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode != KeyEvent.KEYCODE_SPACE && keyCode != KeyEvent.KEYCODE_TAB) {
            TextInputEditText inputEditText = (TextInputEditText) v;

            if (inputEditText != null) {

                switch (inputEditText.getId()) {

                    case R.id.teleop_cargo_ship_hatch_panel_input:
                        teleopCargoShipHatchPanelInputLayout.setError(null);
                        break;

                    case R.id.teleop_cargo_in_cargo_ship_input:
                        teleopCargoInCargoShipInputLayout.setError(null);
                        break;

                    case R.id.teleop_hatch_panel_top_input:
                        teleopHatchPanelTopInputLayout.setError(null);
                        break;

                    case R.id.teleop_hatch_panel_middle_input:
                        teleopHatchPanelMiddleInputLayout.setError(null);
                        break;

                    case R.id.teleop_hatch_panel_bottom_input:
                        teleopHatchPanelBottomInputLayout.setError(null);
                        break;

                    case R.id.teleop_cargo_top_input:
                        teleopCargoTopInputLayout.setError(null);
                        break;

                    case R.id.teleop_cargo_middle_input:
                        teleopCargoMiddleInputLayout.setError(null);
                        break;

                    case R.id.teleop_cargo_bottom_input:
                        teleopCargoBottomInputLayout.setError(null);
                        break;
                }
            }
        }
        return false;
    }

    /*
    * This method will verify that all fields are filled and highlight error to user
    * along with change focus to first blank input area. The radio button values are obtained
    * A file is created on the dvice to send the data to. We add the teleop data to the arraylist
    * delimited by commas. We create our message by concatenating the teleop data to the end of
    * the auton data. The data is then output to the file we created. We send a message to the user
    * about the saved message. We send a result back to the auton activity upon completion.
    * We then clear the data of the teleop activity and finish it to close and return
    * to the auton activty to clear its data*/

    public void saveData(View view) throws IOException {
        String state = Environment.getExternalStorageState();
        boolean allInputsPassed = false;

        if (StringUtils.isEmptyOrNull(getTextInputLayoutString(teleopCargoShipHatchPanelInputLayout))) {
            teleopCargoShipHatchPanelInputLayout.setError(getText(R.string.teleopCargoShipHatchPanelError));
            ViewUtils.requestFocus(teleopCargoShipHatchPanelInputLayout, this);
        } else if (StringUtils.isEmptyOrNull(getTextInputLayoutString(teleopCargoInCargoShipInputLayout))) {
            teleopCargoInCargoShipInputLayout.setError(getText(R.string.teleopCargoInCargoShipError));
            ViewUtils.requestFocus(teleopCargoInCargoShipInputLayout, this);
        } else if (StringUtils.isEmptyOrNull(getTextInputLayoutString(teleopHatchPanelTopInputLayout))) {
            teleopHatchPanelTopInputLayout.setError(getText(R.string.hatchPanelTopError));
            ViewUtils.requestFocus(teleopHatchPanelTopInputLayout, this);
        } else if (StringUtils.isEmptyOrNull(getTextInputLayoutString(teleopHatchPanelMiddleInputLayout))) {
            teleopHatchPanelMiddleInputLayout.setError(getText(R.string.hatchPanelMiddleError));
            ViewUtils.requestFocus(teleopHatchPanelMiddleInputLayout, this);
        } else if (StringUtils.isEmptyOrNull(getTextInputLayoutString(teleopHatchPanelBottomInputLayout))) {
            teleopHatchPanelBottomInputLayout.setError(getText(R.string.hatchPanelBottomError));
            ViewUtils.requestFocus(teleopHatchPanelBottomInputLayout, this);
        } else if (StringUtils.isEmptyOrNull(getTextInputLayoutString(teleopCargoTopInputLayout))) {
            teleopCargoTopInputLayout.setError(getText(R.string.cargoTopError));
            ViewUtils.requestFocus(teleopCargoTopInputLayout, this);
        } else if (StringUtils.isEmptyOrNull(getTextInputLayoutString(teleopCargoMiddleInputLayout))) {
            teleopCargoMiddleInputLayout.setError(getText(R.string.cargoMiddleError));
            ViewUtils.requestFocus(teleopCargoMiddleInputLayout, this);
        } else if (StringUtils.isEmptyOrNull(getTextInputLayoutString(teleopCargoBottomInputLayout))) {
            teleopCargoBottomInputLayout.setError(getText(R.string.cargoBottomError));
            ViewUtils.requestFocus(teleopCargoBottomInputLayout, this);

        } else {
            allInputsPassed = true;
        }
        if (!allInputsPassed) {
            return;
        }

        final RadioButton defenseEffectivenessRadiobtn = findViewById(defenseEffectiveness.getCheckedRadioButtonId());
        final RadioButton overallEffectivenessRadiobtn = findViewById(overallEffectivenessRadioGoup.getCheckedRadioButtonId());
        final RadioButton overallPlacementRadiobtn = findViewById(overallPlacementRadioGoup.getCheckedRadioButtonId());
        final RadioButton trainedDriveTeamRadiobtn = findViewById(trainedDriveTeamRadioGoup.getCheckedRadioButtonId());
        final RadioButton HatchPanelPickupRadiobtn = findViewById(HatchPanelPickup.getCheckedRadioButtonId());
        final RadioButton CargoPickupRadiobtn = findViewById(CargoPickup.getCheckedRadioButtonId());


        if(PermissionUtils.getPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                File dir = new File(Environment.getExternalStorageDirectory() + "/Scouting");
                dir.mkdirs();

                File file = new File(dir, "Match" + Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID) + ".csv");

                teleopDataStringList.add(getTextInputLayoutString(teleopCargoShipHatchPanelInputLayout));
                teleopDataStringList.add(getTextInputLayoutString(teleopCargoInCargoShipInputLayout));
                teleopDataStringList.add(getTextInputLayoutString(teleopHatchPanelTopInputLayout));
                teleopDataStringList.add(getTextInputLayoutString(teleopHatchPanelMiddleInputLayout));
                teleopDataStringList.add(getTextInputLayoutString(teleopHatchPanelBottomInputLayout));
                teleopDataStringList.add(getTextInputLayoutString(teleopCargoTopInputLayout));
                teleopDataStringList.add(getTextInputLayoutString(teleopCargoMiddleInputLayout));
                teleopDataStringList.add(getTextInputLayoutString(teleopCargoBottomInputLayout));
                teleopDataStringList.add(HatchPanelPickupRadiobtn.getText().toString());
                teleopDataStringList.add(CargoPickupRadiobtn.getText().toString());
                teleopDataStringList.add(defenseEffectivenessRadiobtn.getText().toString());
                teleopDataStringList.add(endGameLocationSpinner.getSelectedItem().toString());
                teleopDataStringList.add(cycleTimeSpinner.getSelectedItem().toString());
                teleopDataStringList.add(overallEffectivenessRadiobtn.getText().toString());
                teleopDataStringList.add(overallPlacementRadiobtn.getText().toString());
                teleopDataStringList.add(trainedDriveTeamRadiobtn.getText().toString());
                teleopDataStringList.add(observations);
                teleopDataStringList.add(summaryInput.getText().toString());
                teleopDataStringList.add(issuesInput.getText().toString());

                teleopDataStringList.add(ScouterInitialsActivity.getInitials());

                String message = auton + "," + FormatStringUtils.addDelimiter(teleopDataStringList, ",") + "\n";

                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                    fileOutputStream.write(message.getBytes());
                    fileOutputStream.close();

                    Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "IOException! Go talk to the programmers!", Toast.LENGTH_LONG).show();
                    Log.d("Scouting", e.getMessage());
                }
            } else {
                Toast.makeText(getApplicationContext(), "SD card not found", Toast.LENGTH_LONG).show();
            }

            Intent intent = getIntent();
            intent.putExtra("Key", value);
            setResult(RESULT_OK, intent);

            clearData(view);
            finish();
        }

        teleopCargoShipHatchPanelInputLayout.setError(null);
        teleopCargoInCargoShipInput.setError(null);
        teleopHatchPanelTopInputLayout.setError(null);
        teleopHatchPanelMiddleInputLayout.setError(null);
        teleopHatchPanelBottomInputLayout.setError(null);
        teleopCargoTopInputLayout.setError(null);
        teleopCargoMiddleInputLayout.setError(null);
        teleopCargoBottomInputLayout.setError(null);

    }

    /*The method will clear all the data in the text fields, checkboxes, and
    * set radio buttons to default*/
    public void clearData(View view) {
        teleopCargoShipHatchPanelInput.setText("" + teleopCargoShipHatchPanel);
        teleopCargoInCargoShipInput.setText("" + teleopCargoShipCargo);
        teleopHatchPanelTopInput.setText("" + teleopHatchPanelTop);
        teleopHatchPanelMiddleInput.setText("" + teleopHatchPanelMiddle);
        teleopHatchPanelBottomInput.setText("" + teleopHatchPanelBottom);
        teleopCargoTopInput.setText("" + teleopCargotop);
        teleopCargoMiddleInput.setText("" + teleopCargoMiddle);
        teleopCargoBottomInput.setText("" + teleopCargoBottom);
        defenseEffectiveness.clearCheck();
        endGameLocationSpinner.setSelection(0);
        cycleTimeSpinner.setSelection(0);
        HatchPanelPickup.clearCheck();
        CargoPickup.clearCheck();
        overallEffectivenessRadioGoup.clearCheck();
        overallPlacementRadioGoup.clearCheck();
        trainedDriveTeamRadioGoup.clearCheck();
        observsmooth.setChecked(false);
        observSlowedByRobot.setChecked(false);
        observSlow.setChecked(false);
        observPlayedDefense.setChecked(false);
        observPenalties.setChecked(false);
        observNotMuch.setChecked(false);
        observJerky.setChecked(false);
        observHatchPickup.setChecked(false);
        observFellOver.setChecked(false);
        observFellApart.setChecked(false);
        observFast.setChecked(false);
        observDns.setChecked(false);
        observDiedMid.setChecked(false);
        observDiedBack.setChecked(false);
        observCargoPickup.setChecked(false);
        observAverageSpeed.setChecked(false);
        observDroppedHatches.setChecked(false);
        observDroppedCargo.setChecked(false);
        observHardTimeHatches.setChecked(false);
        observHardTimeCargo.setChecked(false);
        summaryInput.setText(null);
        issuesInput.setText(null);

    }

    /* This method will change the text entered into the app into a string if it is not already*/
    private String getTextInputLayoutString(@NonNull TextInputLayout textInputLayout) {
        final EditText editText = textInputLayout.getEditText();
        return editText != null && editText.getText() != null ? editText.getText().toString() : "";
    }
}
