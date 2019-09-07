package com.example.clothing_app_mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clothing_app_mad.Entites.Customer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserRegister extends AppCompatActivity {

    private Button backBtn, signupBtn;
    private EditText name, email, contactno, crpwrd, cnpwrd;
    private DatabaseReference dbref, childCountDb;

    Customer customer;
    private ProgressDialog progressbar;
    String currentCustomerID;


    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        backBtn = findViewById(R.id.backBtnR);
        signupBtn = findViewById(R.id.singupbtn);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        contactno = findViewById(R.id.contactNo);
        crpwrd = findViewById(R.id.crpwrd);
        cnpwrd = findViewById(R.id.confpwrd);

        childCountDb = FirebaseDatabase.getInstance().getReference().child("Customer");

        progressbar = new ProgressDialog(this);

        customer = new Customer();
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(UserRegister.this, Login.class);
                startActivity(intent);
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerCustomer();
            }
        });


        childCountDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               count = 0;

                for (DataSnapshot ds : dataSnapshot.getChildren()){

                    count++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    public void registerCustomer() {


        dbref = FirebaseDatabase.getInstance().getReference().child("Customer");

        try {

            if (TextUtils.isEmpty(name.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Please Enter the name", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(email.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Enter the email", Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(contactno.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Enter Contact no", Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(cnpwrd.getText().toString())) {
                Toast.makeText(getApplicationContext(), "Enter Your Password", Toast.LENGTH_LONG);

                if (TextUtils.isEmpty(crpwrd.getText().toString())) {

                    Toast.makeText(getApplicationContext(), "Confirm Your Password", Toast.LENGTH_LONG).show();
                }
            } else if (TextUtils.isEmpty(crpwrd.getText().toString())) {

                Toast.makeText(getApplicationContext(), "Confirm Your Password", Toast.LENGTH_LONG).show();
            } else if (crpwrd.getText().toString().equals(cnpwrd.getText().toString())) {


                progressbar.setTitle("Add Customer");
                progressbar.setMessage("Please wait, you are registering to the system");
                progressbar.setCanceledOnTouchOutside(false);
                progressbar.show();


                count++;
                String cid = "C0" + count;

                customer.setCname(name.getText().toString().trim());
                customer.setEmail(email.getText().toString().trim());
                customer.setContactNo(Integer.parseInt(contactno.getText().toString().trim()));
                customer.setPassword(crpwrd.getText().toString().trim());


                dbref.child(cid).setValue(customer);

                Runnable progressRunnable = new Runnable() {

                    @Override
                    public void run() {
                        progressbar.cancel();
                    }
                };

                Handler phandler = new Handler();
                phandler.postDelayed(progressRunnable, 3000);


                Toast.makeText(getApplicationContext(), "Registration Successfull", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UserRegister.this, BottomNav.class);
                startActivity(intent);


            } else {
                Toast.makeText(getApplicationContext(), "Password not match", Toast.LENGTH_LONG).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Invalid Telephone Number", Toast.LENGTH_LONG).show();
        }

    }




}
