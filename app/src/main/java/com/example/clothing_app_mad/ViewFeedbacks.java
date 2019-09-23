package com.example.clothing_app_mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clothing_app_mad.Entites.Feedback;
import com.example.clothing_app_mad.ViewHolder.FeedbackHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ViewFeedbacks extends AppCompatActivity {


   private Button delete;
   private RecyclerView recyclerView;
   private DatabaseReference dbref;
   private RecyclerView.LayoutManager layoutManager;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_view_feedbacks);

        delete = findViewById (R.id.deletebtn);
        recyclerView = findViewById (R.id.feedback_list);
        recyclerView.setHasFixedSize (true);
        layoutManager = new LinearLayoutManager (this);
        recyclerView.setLayoutManager (layoutManager);
        dbref = FirebaseDatabase.getInstance ().getReference ().child ("AddFeedback");

        progressDialog = new ProgressDialog (ViewFeedbacks.this);

    }

    @Override
    protected void onStart() {
        super.onStart ();

        FirebaseRecyclerOptions<Feedback> options =
                new FirebaseRecyclerOptions.Builder<Feedback> ()
                        .setQuery (dbref, Feedback.class).build ();

        FirebaseRecyclerAdapter<Feedback, FeedbackHolder> adapter = new FirebaseRecyclerAdapter<Feedback, FeedbackHolder> (options) {
            @Override
            protected void onBindViewHolder(@NonNull FeedbackHolder FeedbackHolder, final int position, @NonNull Feedback feedback) {
                FeedbackHolder.cname.setText (feedback.getCustname ());
                FeedbackHolder.description.setText (feedback.getDescription ());
                FeedbackHolder.ratingbar.setNumStars (feedback.getRating ());


                FeedbackHolder.delBtn.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder (ViewFeedbacks.this);

                        builder.setMessage ("Are you sure to update?")
                                .setPositiveButton ("Yes", new DialogInterface.OnClickListener () {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        progressDialog.setTitle ("Deleteing");
                                        progressDialog.setMessage ("Please, We are Deleting the Customer record");
                                        progressDialog.setCanceledOnTouchOutside (false);
                                        progressDialog.show ();
                                        System.out.println ("ASADASD" + getRef (position).getKey ());
                                        String uID = getRef (position).getKey ();

                                        RemoveCustomer (uID);
                                        progressDialog.dismiss ();

                                        Toast.makeText (ViewFeedbacks.this, "Successfully Deleted", Toast.LENGTH_SHORT).show ();

                                        startActivity (new Intent (ViewFeedbacks.this, ViewFeedbacks.class));
                                    }
                                }).setNegativeButton ("Cancel", null);

                        AlertDialog alertDialog = builder.create ();
                        alertDialog.show ();

                    }
                });

            }

            @NonNull
            @Override
            public FeedbackHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.feeback_layout, parent, false);
                FeedbackHolder holder = new FeedbackHolder (view);
                return holder;
            }
        };
        recyclerView.setAdapter (adapter);
        adapter.startListening ();

    }

    private void RemoveCustomer(String uID) {


        dbref.child (uID).removeValue ();
    }

}
