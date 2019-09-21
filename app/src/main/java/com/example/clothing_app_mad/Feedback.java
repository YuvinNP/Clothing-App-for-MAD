package com.example.clothing_app_mad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {


    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_feedback);

        ratingBar = (RatingBar)findViewById (R.id.ratingBar);
        ratingBar.setNumStars(5);

        ratingBar.setOnRatingBarChangeListener (new RatingBar.OnRatingBarChangeListener () {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText (Feedback.this, "Starts " + v, Toast.LENGTH_SHORT).show ();
            }
        });
    }
}
