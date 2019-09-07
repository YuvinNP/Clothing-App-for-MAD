package com.example.clothing_app_mad.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothing_app_mad.Interface.ItemClickListner;
import com.example.clothing_app_mad.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtProductName, txtProductPrice;
    public ImageView imageView;
    public ItemClickListner listner;

    public ProductViewHolder(@NonNull View itemView) {

        super(itemView);

        imageView = itemView.findViewById(R.id.product_image);
        txtProductName = itemView.findViewById(R.id.product_name);
        txtProductPrice = itemView.findViewById(R.id.product_price);
    }

    public void setItemClickListner(ItemClickListner listner){
        this.listner = listner;
    }



    @Override
    public void onClick(View view) {

        listner.onClick(view, getAdapterPosition(),false);
    }
}
