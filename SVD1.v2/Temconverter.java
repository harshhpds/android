package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner tempUnitSpinner;
    EditText tempInput;
    TextView resultView;
    Button toCelsiusButton, toFahrenheitButton, toKelvinButton, clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        tempUnitSpinner = findViewById(R.id.tempUnitSpinner);
        tempInput = findViewById(R.id.tempInput);
        resultView = findViewById(R.id.resultView);
        toCelsiusButton = findViewById(R.id.toCelsius);
        toFahrenheitButton = findViewById(R.id.toFahrenheit);
        toKelvinButton = findViewById(R.id.toKelvin);
        clearButton = findViewById(R.id.clearButton);

        // Set up Spinner (Dropdown) for temperature units
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Celsius", "Fahrenheit", "Kelvin"});
        tempUnitSpinner.setAdapter(adapter);

        // Conversion button actions
        toCelsiusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTemperature("Celsius");
            }
        });

        toFahrenheitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTemperature("Fahrenheit");
            }
        });

        toKelvinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTemperature("Kelvin");
            }
        });

        // Clear button action
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempInput.setText("");
                resultView.setText("Output");
            }
        });
    }

    // Function to convert the temperature
    private void convertTemperature(String convertTo) {
        String inputUnit = tempUnitSpinner.getSelectedItem().toString();
        double tempValue = Double.parseDouble(tempInput.getText().toString());
        double result = 0.0;

        switch (inputUnit) {
            case "Celsius":
                if (convertTo.equals("Fahrenheit")) {
                    result = (tempValue * 9/5) + 32;
                } else if (convertTo.equals("Kelvin")) {
                    result = tempValue + 273.15;
                }
                break;
            case "Fahrenheit":
                if (convertTo.equals("Celsius")) {
                    result = (tempValue - 32) * 5/9;
                } else if (convertTo.equals("Kelvin")) {
                    result = (tempValue - 32) * 5/9 + 273.15;
                }
                break;
            case "Kelvin":
                if (convertTo.equals("Celsius")) {
                    result = tempValue - 273.15;
                } else if (convertTo.equals("Fahrenheit")) {
                    result = (tempValue - 273.15) * 9/5 + 32;
                }
                break;
        }

        // Display result
        resultView.setText(String.valueOf(result));
    }
}
