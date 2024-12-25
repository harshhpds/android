package com.example.calender;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the TextView by its ID
        TextView animatedText = findViewById(R.id.animatedText);

        // Create a TranslateAnimation: move from (xStart, yStart) to (xEnd, yEnd)
        TranslateAnimation animation = new TranslateAnimation(
                Animation.ABSOLUTE, 0f,   // Start X
                Animation.ABSOLUTE, 200f, // End X
                Animation.ABSOLUTE, 0f,   // Start Y
                Animation.ABSOLUTE, 200f  // End Y
        );

        // Set animation duration in milliseconds
        animation.setDuration(2000);

        // Set the animation to repeat indefinitely
        animation.setRepeatCount(Animation.INFINITE);

        // Set the repeat mode to reverse
        animation.setRepeatMode(Animation.REVERSE);

        // Start the animation
        animatedText.startAnimation(animation);
    }
}
