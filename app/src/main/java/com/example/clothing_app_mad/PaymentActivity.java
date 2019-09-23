package com.example.clothing_app_mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.clothing_app_mad.Prevalent.Prevalent;

public class PaymentActivity extends AppCompatActivity {


    private TextView totalprice,addressline1, addressline2;
    private String totalAmount = "";
    private String address="";
    private Button btn, next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_payment);

        totalprice = findViewById (R.id.total);
        totalAmount = getIntent().getExtras ().getString ("Total Price");
        System.out.println (totalAmount);
        address = getIntent ().getStringExtra ("address");
        addressline1 = findViewById (R.id.addressL1);
        addressline2 = findViewById (R.id.addressL1);
        btn = findViewById (R.id.backtocart);
        next = findViewById (R.id.next);
        totalprice.setText ("Rs."+Prevalent.total);
        addressline1.setText (""+Prevalent.address);
        addressline2.setText (""+Prevalent.city);

        btn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity (new Intent (PaymentActivity.this, CartActivity.class));
            }
        });

        next.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity (new Intent (PaymentActivity.this, PaymentMethod.class));
            }
        });



    }
}
