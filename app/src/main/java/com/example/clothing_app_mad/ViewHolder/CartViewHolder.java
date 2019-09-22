package com.example.clothing_app_mad.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothing_app_mad.Interface.ItemClickListner;
import com.example.clothing_app_mad.R;

public class CartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView txtProductName, txtProductPrice, txtProductQty;
    public ImageView imageView;

    private ItemClickListner itemClickListner;

    public CartViewHolder(@NonNull View itemView) {

        super( itemView );

        imageView = (ImageView) itemView.findViewById(R.id.cart_product_image);
        txtProductName = (TextView) itemView.findViewById( R.id.cart_product_name);
        txtProductPrice = (TextView) itemView.findViewById( R.id.cart_product_price);
        txtProductQty = (TextView) itemView.findViewById( R.id.cart_product_qty);
    }

    @Override
    public void onClick(View view) {

        itemClickListner.onClick(view, getAdapterPosition(),false);
    }

    public void setItemClickListner(ItemClickListner itemClickListner) {
        this.itemClickListner = itemClickListner;
    }
}
