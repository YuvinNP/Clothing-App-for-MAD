package com.example.clothing_app_mad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    TextView sellerLink, userLink;
    Button loginBtn;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.loginbtn);
        sellerLink = findViewById(R.id.login_as_seller);
        userLink = findViewById(R.id.login_as_user);

        progressDialog = new ProgressDialog(Login.this);

        sellerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginBtn.setText("LOGIN AS A SELLER");
                sellerLink.setVisibility(View.INVISIBLE);
                userLink.setVisibility(View.VISIBLE);
            }
        });

        userLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginBtn.setText("L O G I N");
                userLink.setVisibility(View.INVISIBLE);
                sellerLink.setVisibility(View.VISIBLE);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.setTitle("Please Wait");
                progressDialog.setMessage("You are logging to the system");
                progressDialog.show();
                Intent intent = new Intent(Login.this, UserRegister.class);
                startActivity(intent);
                progressDialog.dismiss();


            }
        });
    }
}
