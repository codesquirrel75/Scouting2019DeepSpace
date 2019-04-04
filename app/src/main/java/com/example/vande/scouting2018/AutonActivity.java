/*

 ***************  Code Designed by Team 107 Team Robotics *********************
 ***************  Edited for Team 1918 By Nate and Ken    *********************


 */

package com.example.vande.scouting2018;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import utils.FormatStringUtils;
import utils.StringUtils;
import utils.ViewUtils;

public class AutonActivity extends AppCompatActivity implements View.OnKeyListener {

    /*This area sets and binds all of the variables that we will use in the auton activity*/
    public static String AUTON_STRING_EXTRA = "auton_extra";

    /* These are the names of the match number and team number extras that will be passed into teleop */
    public static final String MATCH_STRING_EXTRA = "match_extra";
    public static final String TEAMNUMBER_STRING_EXTRA = "teamnumber_extra";


    @BindView(R.id.team_number_spinner)
    public Spinner TeamNumberInputLayout;

    @BindView(R.id.matchNumber_input_layout)
    public TextInputLayout matchNumberInputLayout;

    @BindView(R.id.matchNumber_input)
    public EditText matchNumberInput;

    @BindView(R.id.starting_location)
    public Spinner startingLocation;

    @BindView(R.id.game_piece_pre_loaded)
    public RadioGroup GamePiecePreLoaded;

    @BindView(R.id.moved_off_hab)
    public RadioGroup MovedOffHab;


    @BindView(R.id.play_style)
    public Spinner playStyle;

    @BindView(R.id.decrease_CS_HP)
    public Button decreaseCsHp;

    @BindView(R.id.increase_CS_HP)
    public Button increaseCsHp;

    @BindView(R.id.CS_HP_Layout)
    public TextInputLayout CsHpLayout;

    @BindView(R.id.CS_HP_Input)
    public EditText CsHpInput;

    @BindView(R.id.CS_C_Layout)
    public TextInputLayout CsCLayout;

    @BindView(R.id.CS_C_Input)
    public EditText CsCInput;

    @BindView(R.id.decrease_CS_C)
    public Button decreaseCsC;

    @BindView(R.id.increase_CS_C)
    public Button increaseCsC;

    @BindView(R.id.decrease_R_L_HP)
    public Button decreaseRLHp;

    @BindView(R.id.increase_R_L_HP)
    public Button increaseRLHp;

    @BindView(R.id.R_L_HP_Layout)
    public TextInputLayout RLHpLayout;

    @BindView(R.id.R_L_HP_Input)
    public EditText RLHpInput;

    @BindView(R.id.R_L_C_Layout)
    public TextInputLayout RLCLayout;

    @BindView(R.id.R_L_C_Input)
    public EditText RLCInput;

    @BindView(R.id.increase_R_L_C)
    public Button increaseRLC;

    @BindView(R.id.decrease_R_L_C)
    public Button decreaseRLC;

    @BindView(R.id.decrease_R_M_HP)
    public Button decreaseRMHp;

    @BindView(R.id.increase_R_M_HP)
    public Button increaseRMHp;

    @BindView(R.id.R_M_HP_Layout)
    public TextInputLayout RMHpLayout;

    @BindView(R.id.R_M_HP_Input)
    public EditText RMHpInput;

    @BindView(R.id.R_M_C_Layout)
    public TextInputLayout RMCLayout;

    @BindView(R.id.R_M_C_Input)
    public EditText RMCInput;

    @BindView(R.id.increase_R_M_C)
    public Button increaseRMC;

    @BindView(R.id.decrease_R_M_C)
    public Button decreaseRMC;

    @BindView(R.id.decrease_R_U_HP)
    public Button decreaseRUHp;

    @BindView(R.id.increase_R_U_HP)
    public Button increaseRUHp;

    @BindView(R.id.R_U_HP_Layout)
    public TextInputLayout RUHpLayout;

    @BindView(R.id.R_U_HP_Input)
    public EditText RUHpInput;

    @BindView(R.id.R_U_C_Layout)
    public TextInputLayout RUCLayout;

    @BindView(R.id.R_U_C_Input)
    public EditText RUCInput;

    @BindView(R.id.increase_R_U_C)
    public Button increaseRUHC;

    @BindView(R.id.decrease_R_U_C)
    public Button decreaseRUC;

    @BindView(R.id.next_button)
    public Button nextButton;

    int CargoShipHatchPanel = 0;
    int CargoShipCargo = 0;
    int HatchPanelTop = 0;
    int HatchPanelMiddle = 0;
    int HatchPanelBottom =0;
    int Cargotop = 0;
    int CargoMiddle = 0;
    int CargoBottom = 0;

    private ArrayList<CharSequence> autonDataStringList;
    public static final int REQUEST_CODE = 1;


    /*When this activity is first called,
     *we will call the activity_auton layout so we can display
     *the user interface
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_auton);
        ButterKnife.bind(this);
        autonDataStringList = new ArrayList<>();

        checkForPermissions();

        displayCargoShipHatchPanelInput(CargoShipHatchPanel);
        displayCargoShipCargoInput(CargoShipCargo);
        displayHatchPanelTopInput(HatchPanelTop);
        displayHatchPanelMiddleInput(HatchPanelMiddle);
        displayHatchPanelBottomInput(HatchPanelBottom);
        displayCargoTopInput(Cargotop);
        displayCargoMiddleInput(CargoMiddle);
        displayCargoBottomInput(CargoBottom);

        //  --- Team Numbers spinner ---

        Spinner teamnumberspinner = (Spinner) findViewById(R.id.team_number_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> teamnumberadapter = ArrayAdapter.createFromResource(this,
                R.array.teamNumbers, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        teamnumberadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        teamnumberspinner.setAdapter(teamnumberadapter);

    }

    /*If this activity is resumed from a paused state the data
     *will be set to what they previously were set to
     */
    @Override
    protected void onResume() {
        super.onResume();

        autonDataStringList.clear();

        TeamNumberInputLayout.setOnKeyListener(this);
        matchNumberInput.setOnKeyListener(this);
        startingLocation.setOnKeyListener(this);
        GamePiecePreLoaded.setOnKeyListener(this);
        MovedOffHab.setOnKeyListener(this);
        CsHpInput.setOnKeyListener(this);
        CsCInput.setOnKeyListener(this);
        RLHpInput.setOnKeyListener(this);
        RMHpInput.setOnKeyListener(this);
        RUHpInput.setOnKeyListener(this);
        RLCInput.setOnKeyListener(this);
        RMCInput.setOnKeyListener(this);
        RUCInput.setOnKeyListener(this);
        CsHpLayout.setOnKeyListener(this);
        CsCLayout.setOnKeyListener(this);
        RLHpLayout.setOnKeyListener(this);
        RMHpLayout.setOnKeyListener(this);
        RUHpLayout.setOnKeyListener(this);
        RLCLayout.setOnKeyListener(this);
        RMCLayout.setOnKeyListener(this);
        RUCLayout.setOnKeyListener(this);
        playStyle.setOnKeyListener(this);

    }

    /*If this activity enters a paused state the data will be set to null*/
    @Override
    protected void onPause() {
        super.onPause();

        TeamNumberInputLayout.setOnKeyListener(null);
        matchNumberInput.setOnKeyListener(null);
        startingLocation.setOnKeyListener(null);
        GamePiecePreLoaded.setOnKeyListener(null);
        MovedOffHab.setOnKeyListener(null);
        CsHpInput.setOnKeyListener(null);
        CsCInput.setOnKeyListener(null);
        RLHpInput.setOnKeyListener(null);
        RMHpInput.setOnKeyListener(null);
        RUHpInput.setOnKeyListener(null);
        RLCInput.setOnKeyListener(null);
        RMCInput.setOnKeyListener(null);
        RUCInput.setOnKeyListener(null);
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

    /*Buttons for Cargo Ship*/
    //Hatch Panel
    public void decreaseCargoShipHatchPanelInput(View view) {
        if (CargoShipHatchPanel != 0) {
            CargoShipHatchPanel = CargoShipHatchPanel - 1;
            displayCargoShipHatchPanelInput(CargoShipHatchPanel);
        }
    }

    public void increaseCargoShipHatchPanelInput(View view) {
        if (CargoShipHatchPanel <= 7) {
            CargoShipHatchPanel = CargoShipHatchPanel + 1;
            displayCargoShipHatchPanelInput(CargoShipHatchPanel);
        }
    }

    private void displayCargoShipHatchPanelInput(int number) {
        CsHpInput.setText("" + number);
    }

    //Cargo Ship Cargo
    public void decreaseCargoInCargoShipInput(View view) {
        if (CargoShipCargo != 0) {
            CargoShipCargo = CargoShipCargo - 1;
            displayCargoShipCargoInput(CargoShipCargo);
        }
    }

    public void increaseCargoInCargoShipInput(View view) {
        if (CargoShipCargo <= 7) {
            CargoShipCargo = CargoShipCargo + 1;
            displayCargoShipCargoInput(CargoShipCargo);
        }
    }

    private void displayCargoShipCargoInput(int number) {
        CsCInput.setText("" + number);
    }

    //Teleop Rocket Ship Hatch Panels

    public void decreaseHatchPanelTopInput(View view) {
        if (HatchPanelTop != 0) {
            HatchPanelTop = HatchPanelTop - 1;
            displayHatchPanelTopInput(HatchPanelTop);
        }
    }

    public void increaseHatchPanelTopInput(View view) {
        if (HatchPanelTop <= 3) {
            HatchPanelTop = HatchPanelTop + 1;
            displayHatchPanelTopInput(HatchPanelTop);
        }
    }

    private void displayHatchPanelTopInput(int number) {
        RUHpInput.setText("" + number);
    }

    public void decreaseHatchPanelMiddleInput(View view) {
        if (HatchPanelMiddle != 0) {
            HatchPanelMiddle = HatchPanelMiddle - 1;
            displayHatchPanelMiddleInput(HatchPanelMiddle);
        }
    }

    public void increaseHatchPanelMiddleInput(View view) {
        if (HatchPanelMiddle <= 3) {
            HatchPanelMiddle = HatchPanelMiddle + 1;
            displayHatchPanelMiddleInput(HatchPanelMiddle);
        }
    }

    private void displayHatchPanelMiddleInput(int number) {
        RMHpInput.setText("" + number);
    }

    public void decreaseHatchPanelBottomInput(View view) {
        if (HatchPanelBottom != 0) {
            HatchPanelBottom = HatchPanelBottom - 1;
            displayHatchPanelBottomInput(HatchPanelBottom);
        }
    }

    public void increaseHatchPanelBottomInput(View view) {
        if (HatchPanelBottom <= 3) {
            HatchPanelBottom = HatchPanelBottom + 1;
            displayHatchPanelBottomInput(HatchPanelBottom);
        }
    }

    private void displayHatchPanelBottomInput(int number) {
        RLHpInput.setText("" + number);
    }


    //Teleop Rocket Ship Cargo

    public void decreaseCargoTopInput(View view) {
        if (Cargotop != 0) {
            Cargotop = Cargotop - 1;
            displayCargoTopInput(Cargotop);
        }
    }

    public void increaseCargoTopInput(View view) {
        if (Cargotop <= 3) {
            Cargotop = Cargotop + 1;
            displayCargoTopInput(Cargotop);
        }
    }

    private void displayCargoTopInput(int number) {
        RUCInput.setText("" + number);
    }

    public void decreaseCargoMiddleInput(View view) {
        if (CargoMiddle != 0) {
            CargoMiddle = CargoMiddle - 1;
            displayCargoMiddleInput(CargoMiddle);
        }
    }

    public void increaseCargoMiddleInput(View view) {
        if (CargoMiddle <= 3) {
            CargoMiddle = CargoMiddle + 1;
            displayCargoMiddleInput(CargoMiddle);
        }
    }

    private void displayCargoMiddleInput(int number) {
        RMCInput.setText("" + number);
    }

    public void decreaseCargoBottomInput(View view) {
        if (CargoBottom != 0) {
            CargoBottom = CargoBottom - 1;
            displayCargoBottomInput(CargoBottom);
        }
    }

    public void increaseCargoBottomInput(View view) {
        if (CargoBottom <= 3) {
            CargoBottom = CargoBottom + 1;
            displayCargoBottomInput(CargoBottom);
        }
    }

    private void displayCargoBottomInput(int number) {
        RLCInput.setText("" + number);
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


                    case R.id.matchNumber_input:
                        matchNumberInputLayout.setError(null);
                        break;
                }
            }
        }
        return false;
    }


    /*This method takes place when the Show teleop button is pressed
    *This will first check if the text fields are empty and highlight
    * The area not completed as well as put that text input as the focus
    * to the user in the app. If everything passes by being filled in,
    * The value of the radio buttons will be obtained.
    * Then all of the values of this activity are added to the autonDataStringList
    * delimited by a comma. This method will then launch the teleop activity while sending
    * over our list of data. A request on result is requested so we can clear this aplication
    * after the teleop activity closes
     */
    public void onShowTeleop(View view) {
        boolean allInputsPassed = false;

        if (TeamNumberInputLayout.getSelectedItem().toString().equals("Select Team Number")) {
            setSpinnerError(TeamNumberInputLayout, "Select a Team Number.");
            ViewUtils.requestFocus(TeamNumberInputLayout, this);
        } else if (StringUtils.isEmptyOrNull(getTextInputLayoutString(matchNumberInputLayout)) || Integer.valueOf(getTextInputLayoutString(matchNumberInputLayout)) == 0 || Integer.valueOf(getTextInputLayoutString(matchNumberInputLayout)) >=150) {
            matchNumberInput.setText("");
            matchNumberInputLayout.setError(getText(R.string.matchNumberError));
            ViewUtils.requestFocus(matchNumberInputLayout, this);
        } else {
            allInputsPassed = true;
        }

        if (!allInputsPassed) {
            return;
        }

        final RadioButton PreLoadRadiobtn = findViewById(GamePiecePreLoaded.getCheckedRadioButtonId());
        final RadioButton MovedOffHabBtn = findViewById(MovedOffHab.getCheckedRadioButtonId());


        autonDataStringList.add(TeamNumberInputLayout.getSelectedItem().toString());
        autonDataStringList.add(getTextInputLayoutString(matchNumberInputLayout));
        autonDataStringList.add(startingLocation.getSelectedItem().toString());
        autonDataStringList.add(PreLoadRadiobtn.getText().toString());
        autonDataStringList.add(MovedOffHabBtn.getText().toString());
        autonDataStringList.add(getTextInputLayoutString(CsCLayout));
        autonDataStringList.add(getTextInputLayoutString(CsHpLayout));
        autonDataStringList.add(getTextInputLayoutString(RLHpLayout));
        autonDataStringList.add(getTextInputLayoutString(RMHpLayout));
        autonDataStringList.add(getTextInputLayoutString(RUHpLayout));
        autonDataStringList.add(getTextInputLayoutString(RLCLayout));
        autonDataStringList.add(getTextInputLayoutString(RMCLayout));
        autonDataStringList.add(getTextInputLayoutString(RUCLayout));

//      autonDataStringList.add(playStyle.getSelectedItem().toString());

        final Intent intent = new Intent(this, TeleopActivity.class);
        intent.putExtra(AUTON_STRING_EXTRA, FormatStringUtils.addDelimiter(autonDataStringList, ","));
        intent.putExtra(MATCH_STRING_EXTRA, getTextInputLayoutString(matchNumberInputLayout));
        intent.putExtra(TEAMNUMBER_STRING_EXTRA, TeamNumberInputLayout.getSelectedItem().toString());

        startActivityForResult(intent, REQUEST_CODE);


        matchNumberInputLayout.setError(null);

        matchNumberInput.requestFocus();
    }


    /*This method will check for the result code from the teleop activity
     *so we can clear the data before the next match scouting starts
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
                clearData();
            }
        } catch (Exception ex) {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    /*This method will clear all of the text entry fields as well
    * as reset the checkboxes and reset the radio buttons to their default*/
    public void clearData() {
        TeamNumberInputLayout.setSelection(0);
        matchNumberInput.setText("");
        startingLocation.setSelection(0);
        GamePiecePreLoaded.check(R.id.piece_nothing);
        MovedOffHab.check(R.id.moved_off_hab_yes);
        playStyle.setSelection(0);
        CargoShipHatchPanel = 0;
        CargoShipCargo = 0;
        HatchPanelTop = 0;
        HatchPanelMiddle = 0;
        HatchPanelBottom =0;
        Cargotop = 0;
        CargoMiddle = 0;
        CargoBottom = 0;
        CsHpInput.setText("" + CargoShipHatchPanel);
        CsCInput.setText("" + CargoShipCargo);
        RUHpInput.setText("" + HatchPanelTop);
        RMHpInput.setText("" + HatchPanelMiddle);
        RLHpInput.setText("" + HatchPanelBottom);
        RUCInput.setText("" + Cargotop);
        RMCInput.setText("" + CargoMiddle);
        RLCInput.setText("" + CargoBottom);

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


    /* This method will change the text entered into the app into a string if it is not already*/
    private String getTextInputLayoutString(@NonNull TextInputLayout textInputLayout) {
        final EditText editText = textInputLayout.getEditText();
        return editText != null && editText.getText() != null ? editText.getText().toString() : "";
    }

    private void checkForPermissions() {
        int writePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (writePermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

}
