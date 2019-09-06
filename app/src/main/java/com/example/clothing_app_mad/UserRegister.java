package com.example.clothing_app_mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserRegister extends AppCompatActivity {

    Button backBtn, signupBtn;
    EditText name, email, contactno, pwrd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        backBtn = findViewById(R.id.backBtnR);
        signupBtn = findViewById(R.id.singupbtn);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        contactno = findViewById(R.id.contactNo);
        pwrd = findViewById(R.id.crpwrd);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(UserRegister.this, MainActivity.class);
                startActivity(intent);
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

    }

    public void registerCustomer(){

    }
}
