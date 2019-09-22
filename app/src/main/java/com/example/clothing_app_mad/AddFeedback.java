package com.example.clothing_app_mad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.clothing_app_mad.Entites.Feedback;
import com.example.clothing_app_mad.Prevalent.Prevalent;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFeedback extends AppCompatActivity {


    private Button sendFd;
    private EditText feedback;
    private RatingBar ratingBar;
    private DatabaseReference dbref;
    private Feedback fd = new Feedback ();

    private int num;

    public AddFeedback() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_feedback);

        sendFd = findViewById (R.id.sendfeedback);
        feedback = findViewById (R.id.feedback);
        ratingBar = (RatingBar)findViewById (R.id.ratingBar);
        ratingBar.setNumStars(5);

        final ProgressDialog progressDialog = new ProgressDialog (AddFeedback.this);




        ratingBar.setOnRatingBarChangeListener (new RatingBar.OnRatingBarChangeListener () {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Toast.makeText (AddFeedback.this, "Starts " + v, Toast.LENGTH_SHORT).show ();

                num = (int) v;
                fd.setRating ((int) v);

            }
        });

        sendFd.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {



                progressDialog.setTitle ("Send Feedback");
                progressDialog.setMessage ("Your feedback is sending");
                progressDialog.setCanceledOnTouchOutside (false);
                progressDialog.show ();

                dbref = FirebaseDatabase.getInstance ().getReference ().child ("AddFeedback");

                fd.setRating (num);
                fd.setDescription (feedback.getText ().toString ());
                fd.setCustname (Prevalent.currentOnlineUser.getCname ());

                dbref.push ().setValue (fd);


                System.out.println ("Feedback");

                Toast.makeText (AddFeedback.this, "Sent Feedback Successfully", Toast.LENGTH_SHORT).show ();
                progressDialog.dismiss ();

                startActivity (new Intent (AddFeedback.this, NavDrawer.class));

            }
        });

    }
}
