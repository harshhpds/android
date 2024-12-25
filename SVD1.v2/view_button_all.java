package com.example.pract1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private RadioGroup genderGroup;
    private CheckBox subscribeCheckBox;
    private AutoCompleteTextView countryAutoComplete;
    private ImageButton clearButton;
    private ToggleButton enableToggle;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = findViewById(R.id.username);
        genderGroup = findViewById(R.id.genderGroup);
        subscribeCheckBox = findViewById(R.id.subscribeCheckBox);
        countryAutoComplete = findViewById(R.id.countryAutoComplete);
        clearButton = findViewById(R.id.clearButton);
        enableToggle = findViewById(R.id.enableToggle);
        submitButton = findViewById(R.id.submitButton);

        // AutoCompleteTextView setup
        String[] countries = {"India", "United States", "Canada", "Australia", "United Kingdom"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, countries);
        countryAutoComplete.setAdapter(adapter);

        // Clear button functionality
        clearButton.setOnClickListener(v -> {
            usernameEditText.setText("");
            genderGroup.clearCheck();
            subscribeCheckBox.setChecked(false);
            countryAutoComplete.setText("");
            Toast.makeText(this, "Form Cleared", Toast.LENGTH_SHORT).show();
        });

        // Submit button functionality
        submitButton.setOnClickListener(v -> {
            String name = usernameEditText.getText().toString();
            int selectedGenderId = genderGroup.getCheckedRadioButtonId();
            String gender = selectedGenderId != -1
                    ? ((RadioButton) findViewById(selectedGenderId)).getText().toString()
                    : "Not Selected";
            String country = countryAutoComplete.getText().toString();
            boolean isSubscribed = subscribeCheckBox.isChecked();

            if (!enableToggle.isChecked()) {
                Toast.makeText(this, "Please enable the toggle to submit the form", Toast.LENGTH_SHORT).show();
                return;
            }

            String message = "Name: " + name +
                    "\nGender: " + gender +
                    "\nCountry: " + country +
                    "\nSubscribed: " + (isSubscribed ? "Yes" : "No");
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        });
    }
}
