/*

 ***************  Code Designed by Team 107 Team Robotics *********************
 ***************  Edited for Team 1918 By Nate and Ken    *********************


 */

package com.example.vande.scouting2018;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import utils.FormatStringUtils;
import utils.PermissionUtils;
import utils.StringUtils;
import utils.ViewUtils;

/**
 * Created by Matt from Team 107 on 9/30/2017.
 * Borrowed by Ken from Team 1918 on 1/7/2019.
 */

public class PitActivity extends AppCompatActivity implements View.OnKeyListener {

    @BindView(R.id.pit_team_number_spinner)
    public Spinner pitTeamNumberInputLayout;

    @BindView(R.id.pit_robot_weight)
    public EditText pitRobotWeight;

    @BindView(R.id.pit_drive_train_spinner)
    public Spinner pitDriveTrainInputLayout;

    @BindView(R.id.pit_programming_language_spinner)
    public Spinner pitProgrammingLanguages;

    @BindView(R.id.pit_other_input)
    public EditText pitOtherInputLayout;

    @BindView(R.id.pit_starting_hab_position_spinner)
    public Spinner pitStartingHabPositionSpinner;

    @BindView(R.id.pit_defense_in_perimeter)
    public RadioGroup pitDefenseInPerimeter;

    @BindView(R.id.pit_game_piece_pre_loaded)
    public  RadioGroup pitGamePiecePreLoaded;

    @BindView(R.id.pit_prematch_radio_group)
    public  RadioGroup pitPrematchRadioGroup;

    public String pit15String = "";

    public String pitHatchString = "";

    public  String pitCargoString = "";

    @BindView(R.id.pit_endgame)
    public Spinner pitEndgame;

    @BindView(R.id.pit_15_auton)
    public CheckBox pit15Auton;

    @BindView(R.id.pit_15_manual)
    public CheckBox pit15Manual;

    @BindView(R.id.pit_15_nothing)
    public CheckBox pit15Nothing;

    @BindView(R.id.pit_hatch_cargo)
    public CheckBox pitHatchCargo;

    @BindView(R.id.pit_hatch_top)
    public CheckBox pitHatchTop;

    @BindView(R.id.pit_hatch_middle)
    public CheckBox pitHatchMiddle;

    @BindView(R.id.pit_hatch_bottom)
    public CheckBox pitHatchBottom;

    @BindView(R.id.pit_hatch_na)
    public CheckBox pitHatchNa;

    @BindView(R.id.pit_cargo_cargo)
    public CheckBox pitCargoCargo;

    @BindView(R.id.pit_cargo_top)
    public CheckBox pitCargoTop;

    @BindView(R.id.pit_cargo_middle)
    public CheckBox pitCargoMiddle;

    @BindView(R.id.pit_cargo_bottom)
    public CheckBox pitCargoBottom;

    @BindView(R.id.pit_cargo_na)
    public CheckBox pitCargoNa;



    @BindView(R.id.scouterInitials_input)
    public EditText scouterInitialsInput;


    @BindView(R.id.take_photo_btn)
    public Button takePhotoBtn;

    @BindView(R.id.save_pit_btn)
    public Button savePitBtn;

    private ArrayList<CharSequence> pitDataStringList;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pit);
        pitDataStringList = new ArrayList<>();

        ButterKnife.bind(this);

        checkForPermissions();



        //  --- Drive Train spinner ---

        Spinner drivetrainspinner = (Spinner) findViewById(R.id.pit_drive_train_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> drivetrainadapter = ArrayAdapter.createFromResource(this,
                R.array.driveTrain, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        drivetrainadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        drivetrainspinner.setAdapter(drivetrainadapter);


        //  --- Team Numbers spinner ---

        Spinner teamnumberspinner = (Spinner) findViewById(R.id.pit_team_number_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> teamnumberadapter = ArrayAdapter.createFromResource(this,
                R.array.teamNumbers, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        teamnumberadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        teamnumberspinner.setAdapter(teamnumberadapter);



        //  --- Programming languages spinner  ---

        Spinner languagespinner = (Spinner) findViewById(R.id.pit_programming_language_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> languageadapter = ArrayAdapter.createFromResource(this,
                R.array.programmingLanguages, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        languageadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        languagespinner.setAdapter(languageadapter);


        //  ---  Hab starting position spinner  ---

        Spinner habspinner = (Spinner) findViewById(R.id.pit_starting_hab_position_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> habadapter = ArrayAdapter.createFromResource(this, R.array.habPlatform, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        habadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        habspinner.setAdapter(habadapter);

        //  ---  EndGame spinner  ---

        Spinner endgamespinner = (Spinner) findViewById(R.id.pit_endgame);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> endgameadapter = ArrayAdapter.createFromResource(this, R.array.endgame_location, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        endgameadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        endgamespinner.setAdapter(endgameadapter);
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


    public void setString(View view){
        Boolean checked = ((CheckBox)view).isChecked();
        String s1;

        switch (view.getId()){
            case R.id.pit_15_auton:
                s1 = " Autonomous |";
                if(checked){
                    if(pit15String.isEmpty()) {
                        pit15String = s1;
                    }else{
                        pit15String = pit15String + s1;
                    }
                }else{
                    if(pit15String.contains(s1)){
                        int start = pit15String.indexOf(s1);
                        pit15String = pit15String.substring(0,start) + pit15String.substring(start + s1.length());
                    }
                }
                break;

            case R.id.pit_15_manual:
                s1 = "Manual |";
                if(checked){
                    if(pit15String.isEmpty()) {
                        pit15String = s1;
                    }else{
                        pit15String = pit15String + s1;
                    }

                }else{
                    if(pit15String.contains(s1)){
                        int start = pit15String.indexOf(s1);
                        pit15String = pit15String.substring(0,start) + pit15String.substring(start + s1.length());
                    }
                }
                break;

            case R.id.pit_15_nothing:
                s1 = "Nothing |";
                if(checked){
                    if(pit15String.isEmpty()) {
                        pit15String = s1;
                    }else{
                        pit15String = pit15String + s1;
                    }
                }else{
                    if(pit15String.contains(s1)){
                        int start = pit15String.indexOf(s1);
                        pit15String = pit15String.substring(0,start) + pit15String.substring(start + s1.length());
                    }
                }
                break;

            case R.id.pit_hatch_cargo:
                s1 = "Cargo |";
                if(checked){
                    if(pitHatchString.isEmpty()) {
                        pitHatchString = s1;
                    }else{
                        pitHatchString = pitHatchString + s1;
                    }
                }else{
                    if(pitHatchString.contains(s1)){
                        int start = pitHatchString.indexOf(s1);
                        pitHatchString = pitHatchString.substring(0,start) + pitHatchString.substring(start + s1.length());
                    }
                }
                break;

            case R.id.pit_hatch_bottom:
                s1 = "Bottom |";
                if(checked){
                    if(pitHatchString.isEmpty()) {
                        pitHatchString = s1;
                    }else{
                        pitHatchString = pitHatchString + s1;
                    }
                }else{
                    if(pitHatchString.contains(s1)){
                        int start = pitHatchString.indexOf(s1);
                        pitHatchString = pitHatchString.substring(0,start) + pitHatchString.substring(start + s1.length());
                    }
                }
                break;

            case R.id.pit_hatch_middle:
                s1 = "Middle |";
                if(checked){
                    if(pitHatchString.isEmpty()) {
                        pitHatchString = s1;
                    }else{
                        pitHatchString = pitHatchString + s1;
                    }
                }else{
                    if(pitHatchString.contains(s1)){
                        int start = pitHatchString.indexOf(s1);
                        pitHatchString = pitHatchString.substring(0,start) + pitHatchString.substring(start + s1.length());
                    }
                }
                break;

            case R.id.pit_hatch_top:
                s1 = "Top |";
                if(checked){
                    if(pitHatchString.isEmpty()) {
                        pitHatchString = s1;
                    }else{
                        pitHatchString = pitHatchString + s1;
                    }
                }else{
                    if(pitHatchString.contains(s1)){
                        int start = pitHatchString.indexOf(s1);
                        pitHatchString = pitHatchString.substring(0,start) + pitHatchString.substring(start + s1.length());
                    }
                }
                break;

            case R.id.pit_hatch_na:
                s1 = "N/A |";
                if(checked){
                    if(pitHatchString.isEmpty()) {
                        pitHatchString = s1;
                    }else{
                        pitHatchString = pitHatchString + s1;
                    }
                }else{
                    if(pitHatchString.contains(s1)){
                        int start = pitHatchString.indexOf(s1);
                        pitHatchString = pitHatchString.substring(0,start) + pitHatchString.substring(start + s1.length());
                    }
                }
                break;

            case R.id.pit_cargo_cargo:
                s1 = "Cargo |";
                if(checked){
                    if(pitCargoString.isEmpty()) {
                        pitCargoString = s1;
                    }else{
                        pitCargoString = pitCargoString + s1;
                    }
                }else{
                    if(pitCargoString.contains(s1)){
                        int start = pitCargoString.indexOf(s1);
                        pitCargoString = pitCargoString.substring(0,start) + pitCargoString.substring(start + s1.length());
                    }
                }
                break;

            case R.id.pit_cargo_bottom:
                s1 = "Bottom |";
                if(checked){
                    if(pitCargoString.isEmpty()) {
                        pitCargoString = s1;
                    }else{
                        pitCargoString = pitCargoString + s1;
                    }
                }else{
                    if(pitCargoString.contains(s1)){
                        int start = pitCargoString.indexOf(s1);
                        pitCargoString = pitCargoString.substring(0,start) + pitCargoString.substring(start + s1.length());
                    }
                }
                break;

            case R.id.pit_cargo_middle:
                s1 = "Middle |";
                if(checked){
                    if(pitCargoString.isEmpty()) {
                        pitCargoString = s1;
                    }else{
                        pitCargoString = pitCargoString + s1;
                    }
                }else{
                    if(pitCargoString.contains(s1)){
                        int start = pitCargoString.indexOf(s1);
                        pitCargoString = pitCargoString.substring(0,start) + pitCargoString.substring(start + s1.length());
                    }
                }
                break;

            case R.id.pit_cargo_top:
                s1 = "Top |";
                if(checked){
                    if(pitCargoString.isEmpty()) {
                        pitCargoString = s1;
                    }else{
                        pitCargoString = pitCargoString + s1;
                    }
                }else{
                    if(pitCargoString.contains(s1)){
                        int start = pitCargoString.indexOf(s1);
                        pitCargoString = pitCargoString.substring(0,start) + pitCargoString.substring(start + s1.length());
                    }
                }
                break;

            case R.id.pit_cargo_na:
                s1 = "N/A |";
                if(checked){
                    if(pitCargoString.isEmpty()) {
                        pitCargoString = s1;
                    }else{
                        pitCargoString = pitCargoString + s1;
                    }
                }else{
                    if(pitCargoString.contains(s1)){
                        int start = pitCargoString.indexOf(s1);
                        pitCargoString = pitCargoString.substring(0,start) + pitCargoString.substring(start + s1.length());
                    }
                }
                break;

        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        pitTeamNumberInputLayout.setOnKeyListener(this);
        pitDriveTrainInputLayout.setOnKeyListener(this);
        pitOtherInputLayout.setOnKeyListener(this);
        pitRobotWeight.setOnKeyListener(this);
        pitProgrammingLanguages.setOnKeyListener(this);
        pitStartingHabPositionSpinner.setOnKeyListener(this);
        pitDefenseInPerimeter.setOnKeyListener(this);
        pitGamePiecePreLoaded.setOnKeyListener(this);
        pitEndgame.setOnKeyListener(this);
    }


    @Override
    protected void onPause() {
        super.onPause();

        pitTeamNumberInputLayout.setOnKeyListener(null);
        pitDriveTrainInputLayout.setOnKeyListener(null);
        pitOtherInputLayout.setOnKeyListener(null);
        pitRobotWeight.setOnKeyListener(null);
        pitProgrammingLanguages.setOnKeyListener(null);
        pitStartingHabPositionSpinner.setOnKeyListener(null);
        pitDefenseInPerimeter.setOnKeyListener(null);
        pitGamePiecePreLoaded.setOnKeyListener(null);
        pitEndgame.setOnKeyListener(null);
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {


        return false;
    }

    public void savePitData(View view) throws IOException {
        String state = Environment.getExternalStorageState();
        boolean allInputsPassed = false;

        //  ******  Check Requiered fields set focus to field if it hasn't been filled out  ******


        if (pitTeamNumberInputLayout.getSelectedItem().toString().equals("Select Team Number") ) {
           setSpinnerError(pitTeamNumberInputLayout, "Select a Team Number.");
           ViewUtils.requestFocus(pitTeamNumberInputLayout, this);
        }else if (pitProgrammingLanguages.getSelectedItem().toString().equals("") ) {
            setSpinnerError(pitProgrammingLanguages, "Select a Programming Language.");
            ViewUtils.requestFocus(pitProgrammingLanguages, this);
        }else if(pitDriveTrainInputLayout.getSelectedItem().toString().equals("")){
            setSpinnerError(pitDriveTrainInputLayout, "Select a drive train.");
            ViewUtils.requestFocus(pitDriveTrainInputLayout, this);
        }else if(pitStartingHabPositionSpinner.getSelectedItem().toString().equals("")){
            setSpinnerError(pitStartingHabPositionSpinner, "Select a drive train.");
            ViewUtils.requestFocus(pitStartingHabPositionSpinner, this);
        } else if (pitGamePiecePreLoaded.getCheckedRadioButtonId() == -1) {
            ViewUtils.requestFocus(pitGamePiecePreLoaded, this);
        } else if (StringUtils.isEmptyOrNull(pitRobotWeight.getText().toString())) {
            pitRobotWeight.setError(getText(R.string.pitRobotWeightError));
            ViewUtils.requestFocus(pitRobotWeight, this);
        } else if (StringUtils.isEmptyOrNull(scouterInitialsInput.getText().toString())) {
            scouterInitialsInput.setError(getText(R.string.scouterInitialsError));
            ViewUtils.requestFocus(scouterInitialsInput, this);
        } else {
            allInputsPassed = true;
        }
        if (!allInputsPassed) {
            return;
        }

      final RadioButton pitPreLoadRadiobtn = findViewById(pitGamePiecePreLoaded.getCheckedRadioButtonId());
      final RadioButton pitPrematchRadiobtn = findViewById(pitPrematchRadioGroup.getCheckedRadioButtonId());
      final RadioButton pitDefenseInPerimeterbtn = findViewById(pitDefenseInPerimeter.getCheckedRadioButtonId());

        if(PermissionUtils.getPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                File dir = new File(Environment.getExternalStorageDirectory() + "/Scouting");
                //create csv file
                File file = new File(dir, "Pit" + Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID) + ".csv");

                pitDataStringList.add(pitTeamNumberInputLayout.getSelectedItem().toString());
                pitDataStringList.add(pitRobotWeight.getText().toString());
                pitDataStringList.add(pitDriveTrainInputLayout.getSelectedItem().toString());
                pitDataStringList.add(pitProgrammingLanguages.getSelectedItem().toString());
                pitDataStringList.add(pitStartingHabPositionSpinner.getSelectedItem().toString());
                pitDataStringList.add(pitDefenseInPerimeterbtn.getText().toString());
                pitDataStringList.add(pitPreLoadRadiobtn.getText().toString());
                pitDataStringList.add(pit15String);
                pitDataStringList.add(pitHatchString);
                pitDataStringList.add(pitCargoString);
                pitDataStringList.add(pitEndgame.getSelectedItem().toString());
                pitDataStringList.add(pitPrematchRadiobtn.getText().toString());
                pitDataStringList.add(pitOtherInputLayout.getText().toString());
                pitDataStringList.add(scouterInitialsInput.getText().toString());



                String message = FormatStringUtils.addDelimiter(pitDataStringList, ",") + "\n";


                //Output data to file
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

            clearData();
            pitTeamNumberInputLayout.requestFocus();
        }

        pitDataStringList.clear();


    }

    public void takePhoto(View view) {
        String name = pitTeamNumberInputLayout.getSelectedItem().toString();

        if(PermissionUtils.getPermissions(this, Manifest.permission.CAMERA) &&
                PermissionUtils.getPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) &&
                PermissionUtils.getPermissions(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            if (!StringUtils.isEmptyOrNull(name)) {
                File dir = new File(Environment.getExternalStorageDirectory() + "/Scouting/Photos");
                dir.mkdirs();

                File file = new File(dir, name + ".jpg");

                try {
                    file.createNewFile();
                } catch (IOException e) {
                    Log.d("Scouting", e.getMessage());
                }

                Uri outputUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", file);

                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
                    startActivityForResult(takePictureIntent, 0);
                }
            } else {
                //setSpinnerError(pitTeamNumberInputLayout, "Select a Team Number.");
                ViewUtils.requestFocus(pitTeamNumberInputLayout, this);
            }
        } else {
            checkForPermissions();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if(resultCode == RESULT_OK) {
                compressPhoto();
            }
        }
    }

    private void compressPhoto() {
        try {
            String name = pitTeamNumberInputLayout.getSelectedItem().toString();

            File dir = new File(Environment.getExternalStorageDirectory() + "/Scouting/Photos");
            File file = new File(dir, name + ".jpg");

            FileInputStream inputStream = new FileInputStream(file);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 25, out);

            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(out.toByteArray());
            inputStream.close();
            out.close();
            outputStream.close();

            Toast.makeText(this, "Photo taken!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Log.d("Scouting", e.getMessage());
            Toast.makeText(this, "Failed to save photo. Try again!", Toast.LENGTH_LONG).show();
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

    public void clearData() {
        pitTeamNumberInputLayout.setSelection(0);
        pitRobotWeight.setText(null);
        pitDriveTrainInputLayout.setSelection(0);
        pitProgrammingLanguages.setSelection(0);
        pitStartingHabPositionSpinner.setSelection(0);
        pitDefenseInPerimeter.check(R.id.defense_in_perimeter_no);
        pitGamePiecePreLoaded.check(R.id.piece_nothing);
        pitPrematchRadioGroup.check(R.id.pit_prematch_nothing);
        pitOtherInputLayout.setText(null);
        pitEndgame.setSelection(0);
        scouterInitialsInput.setText(null);
        pit15Auton.setChecked(false);
        pit15Manual.setChecked(false);
        pit15Nothing.setChecked(false);
        pitCargoCargo.setChecked(false);
        pitCargoTop.setChecked(false);
        pitCargoMiddle.setChecked(false);
        pitCargoBottom.setChecked(false);
        pitCargoNa.setChecked(false);
        pitHatchCargo.setChecked(false);
        pitHatchTop.setChecked(false);
        pitHatchMiddle.setChecked(false);
        pitHatchBottom.setChecked(false);
        pitHatchNa.setChecked(false);
    }

    private void checkForPermissions() {
        int cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }
    }

    private String getTextInputLayoutString(@NonNull TextInputLayout textInputLayout) {
        final EditText editText = textInputLayout.getEditText();
        return editText != null && editText.getText() != null ? editText.getText().toString() : "";
    }
}
