package com.example.clothing_app_mad.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothing_app_mad.R;

public class FeedbackHolder extends RecyclerView.ViewHolder {

    public RatingBar ratingbar;
    public TextView cname, description;
    public Button delBtn;

    public FeedbackHolder(@NonNull View itemView) {
        super (itemView);

        ratingbar = (RatingBar)itemView.findViewById (R.id.ratingBarShow);
        cname = itemView.findViewById (R.id.customerName);
        description = itemView.findViewById (R.id.description);
        delBtn = itemView.findViewById (R.id.deletebtn);
    }
}
