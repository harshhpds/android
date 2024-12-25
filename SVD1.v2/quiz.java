package com.example.calender;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private TextView questionText;
    private RadioGroup optionsGroup;
    private Button submitButton;
    private TextView scoreText;

    private int currentQuestionIndex = 0;
    private int score = 0;

    private String[] questions = {
            "What is the capital of France?",
            "Who developed Java Programming?",
            "What is the result of 5 + 3?"
    };

    private String[][] options = {
            {"Berlin", "Madrid", "Paris", "Rome"},
            {"James Gosling", "Dennis Ritchie", "Guido van Rossum", "Bjarne Stroustrup"},
            {"6", "7", "8", "9"}
    };

    private int[] correctAnswers = {2, 0, 2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionText = findViewById(R.id.questionText);
        optionsGroup = findViewById(R.id.optionsGroup);
        submitButton = findViewById(R.id.submitButton);
        scoreText = findViewById(R.id.scoreText);

        loadQuestion();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedOptionId = optionsGroup.getCheckedRadioButtonId();

                if (selectedOptionId == -1) {
                    Toast.makeText(MainActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                } else {
                    RadioButton selectedOption = findViewById(selectedOptionId);
                    int selectedIndex = optionsGroup.indexOfChild(selectedOption);

                    if (selectedIndex == correctAnswers[currentQuestionIndex]) {
                        score++;
                        Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Wrong Answer!", Toast.LENGTH_SHORT).show();
                    }

                    currentQuestionIndex++;

                    if (currentQuestionIndex < questions.length) {
                        loadQuestion();
                    } else {
                        showFinalScore();
                    }
                }
            }
        });
    }

    private void loadQuestion() {
        questionText.setText(questions[currentQuestionIndex]);
        optionsGroup.clearCheck();
        optionsGroup.removeAllViews();

        for (int i = 0; i < options[currentQuestionIndex].length; i++) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(options[currentQuestionIndex][i]);
            optionsGroup.addView(radioButton);
        }
    }

    private void showFinalScore() {
        questionText.setText("Quiz Completed!");
        optionsGroup.setVisibility(View.GONE);
        submitButton.setVisibility(View.GONE);
        scoreText.setText("Your final score is: " + score + "/" + questions.length);
    }
}
