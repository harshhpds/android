package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText display;
    private String currentOperator = "";
    private double firstValue = 0.0;
    private boolean isNewOperation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // Button Click Handlers
        View.OnClickListener listener = v -> {
            Button button = (Button) v;
            String buttonText = button.getText().toString();

            if (buttonText.matches("[0-9]")) { // Numbers
                if (isNewOperation) {
                    display.setText(buttonText);
                    isNewOperation = false;
                } else {
                    display.append(buttonText);
                }
            } else if (buttonText.matches("[+\\-*/]")) { // Operators
                currentOperator = buttonText;
                firstValue = Double.parseDouble(display.getText().toString());
                isNewOperation = true;
            } else if (buttonText.equals("=")) { // Equals
                double secondValue = Double.parseDouble(display.getText().toString());
                double result = calculate(firstValue, secondValue, currentOperator);
                display.setText(String.valueOf(result));
                isNewOperation = true;
            } else if (buttonText.equals("C")) { // Clear
                display.setText("");
                currentOperator = "";
                firstValue = 0.0;
                isNewOperation = true;
            }
        };

        // Attach listener to buttons
        int[] buttonIds = {R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6,
                R.id.btn_7, R.id.btn_8, R.id.btn_9, R.id.btn_add, R.id.btn_subtract, R.id.btn_multiply, R.id.btn_divide,
                R.id.btn_equals, R.id.btn_clear};

        for (int id : buttonIds) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private double calculate(double a, double b, String operator) {
        switch (operator) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return b != 0 ? a / b : 0; // Handle divide by zero
            default: return 0;
        }
    }
}
