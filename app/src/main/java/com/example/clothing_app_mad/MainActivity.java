package com.example.clothing_app_mad;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.clothing_app_mad.Seller.SellerRegiser;

public class MainActivity extends AppCompatActivity {

    Button join, signin;
    Button becomSeller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        join = findViewById(R.id.joinBtn);
        signin = findViewById(R.id.signInbtn);
        becomSeller = findViewById (R.id.selllerreg);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserRegister.class);
                startActivity(intent);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
            }
        });

        becomSeller.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, SellerRegiser.class);
                startActivity (intent);
            }
        });
    }
}
