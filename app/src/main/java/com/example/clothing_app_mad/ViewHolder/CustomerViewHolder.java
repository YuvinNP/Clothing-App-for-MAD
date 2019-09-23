package com.example.clothing_app_mad.ViewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothing_app_mad.Interface.ItemClickListner;
import com.example.clothing_app_mad.R;

public class CustomerViewHolder  extends RecyclerView.ViewHolder{

    public TextView cname, email;
    public ImageView imageView;
    public Button delete;
    public ItemClickListner listner;

    public CustomerViewHolder(@NonNull View itemView) {
        super (itemView);

        email = (TextView)itemView.findViewById (R.id.customer_email);
        cname = (TextView)itemView.findViewById (R.id.customer_name);
        imageView = itemView.findViewById (R.id.customer_image);
        delete = itemView.findViewById (R.id.delete);
    }
    public void setItemClickListner(ItemClickListner listner){

        this.listner = listner;
    }


}
