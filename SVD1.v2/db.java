package com.example.dbconnection;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editTextName, editTextEmail, editTextAge;
    private Button buttonInsert, buttonDisplay;
    private TextView textViewResults, textViewConnectionStatus;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextAge = findViewById(R.id.editTextAge);
        buttonInsert = findViewById(R.id.buttonInsert);
        buttonDisplay = findViewById(R.id.buttonDisplay);
        textViewResults = findViewById(R.id.textViewResults);
        textViewConnectionStatus = findViewById(R.id.textViewConnectionStatus);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Check database connection
        checkDatabaseConnection();

        // Insert data into SQLite
        buttonInsert.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String email = editTextEmail.getText().toString();
            int age = Integer.parseInt(editTextAge.getText().toString());
            databaseHelper.insertUser(name, email, age);
        });

        // Display all data from SQLite
        buttonDisplay.setOnClickListener(v -> displayUsers());
    }

    // Check database connection
    private void checkDatabaseConnection() {
        textViewConnectionStatus.setText("Database is Ready");
        textViewConnectionStatus.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
    }

    // Display all users from the database
    private void displayUsers() {
        Cursor cursor = databaseHelper.getAllUsers();
        StringBuilder results = new StringBuilder();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String email = cursor.getString(cursor.getColumnIndex("email"));
                int age = cursor.getInt(cursor.getColumnIndex("age"));
                results.append("Name: ").append(name).append("\n")
                        .append("Email: ").append(email).append("\n")
                        .append("Age: ").append(age).append("\n\n");
            } while (cursor.moveToNext());
        }

        // Set the results to TextView
        textViewResults.setText(results.toString());
    }
}
