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
import com.example.clothing_app_mad.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserRegister extends AppCompatActivity {

    private Button backBtn, signupBtn;
    private EditText name, email, contactno, crpwrd, cnpwrd, addressLine1, addressLine2, addressLine3;
    private DatabaseReference dbref, childCountDb;

    Customer customer;
    private ProgressDialog progressbar;
    String currentCustomerID;

    private FirebaseAuth firebaseAuth;


    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);

        firebaseAuth = FirebaseAuth.getInstance();

        backBtn = findViewById(R.id.backBtnR);
        signupBtn = findViewById(R.id.singupbtn);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        contactno = findViewById(R.id.contactNo);
        crpwrd = findViewById(R.id.crpwrd);
        cnpwrd = findViewById(R.id.confpwrd);
        addressLine1 = findViewById (R.id.address1);
        addressLine2 = findViewById (R.id.address2);
        addressLine3 = findViewById (R.id.address3);

        childCountDb = FirebaseDatabase.getInstance().getReference().child("Customer");

        progressbar = new ProgressDialog(this);


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

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){

                    count++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    public void registerCustomer() {

        final String cname = name.getText ().toString ();
        final String cemail = email.getText ().toString ();
        final int contactNo = Integer.parseInt (contactno.getText ().toString ());
        final String password = cnpwrd.getText ().toString ();
        final String addressline1 = addressLine1.getText ().toString ();
        final String addressline2 = addressLine2.getText ().toString ();
        final String district = addressLine3.getText ().toString ();


        dbref = FirebaseDatabase.getInstance().getReference().child("Customer");

        try {

            if (TextUtils.isEmpty (cname) ){
                Toast.makeText (getApplicationContext (), "Please Enter the name", Toast.LENGTH_SHORT).show ();
            } else if (TextUtils.isEmpty (cemail)) {
                Toast.makeText (getApplicationContext (), "Enter the email", Toast.LENGTH_LONG).show ();
            } else if (TextUtils.isEmpty (contactno.getText ().toString ())) {
                Toast.makeText (getApplicationContext (), "Enter Contact no", Toast.LENGTH_LONG).show ();
            } else if (TextUtils.isEmpty (password)) {
                Toast.makeText (getApplicationContext (), "Enter Your Password", Toast.LENGTH_LONG);

                if (TextUtils.isEmpty (crpwrd.getText ().toString ())) {

                    Toast.makeText (getApplicationContext (), "Confirm Your Password", Toast.LENGTH_LONG).show ();
                }
            } else if (TextUtils.isEmpty (crpwrd.getText ().toString ())) {

                Toast.makeText (getApplicationContext (), "Confirm Your Password", Toast.LENGTH_LONG).show ();
            } else if (TextUtils.isEmpty (addressline1)) {

                Toast.makeText (getApplicationContext (), "Fill Address Line 1", Toast.LENGTH_LONG).show ();

            } else if (TextUtils.isEmpty (addressline2)) {

                Toast.makeText (getApplicationContext (), "Fill Address Line 2", Toast.LENGTH_LONG).show ();

            }else if(TextUtils.isEmpty (district)){

                Toast.makeText (getApplicationContext (), "Enter District", Toast.LENGTH_LONG).show ();

            }else if (crpwrd.getText().toString().equals(cnpwrd.getText().toString())) {


                progressbar.setTitle("Add Customer");
                progressbar.setMessage("Please wait, you are registering to the system");
                progressbar.setCanceledOnTouchOutside(false);
                progressbar.show();

                DatabaseReference dbref;
                dbref = FirebaseDatabase.getInstance ().getReference ();

                dbref.addListenerForSingleValueEvent (new ValueEventListener () {
                    @Override
                    public void onDataChange(final DataSnapshot dataSnapshot) {

                        (firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), crpwrd.getText().toString()))
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        progressbar.dismiss();

                                        if(task.isSuccessful()){


                                            Customer customer = new Customer (
                                                    cname,
                                                    cemail,
                                                    contactNo,
                                                    addressline1,
                                                    addressline2,
                                                    district,
                                                    password

                                            );
                                            System.out.println ("asasdasd"+FirebaseDatabase.getInstance ().getReference ("Customer").child (FirebaseAuth.getInstance ().getCurrentUser ().getUid()));

                                            FirebaseDatabase.getInstance ().getReference ("Customer").child (FirebaseAuth.getInstance ().getCurrentUser ().getUid ())
                                                    .setValue (customer).addOnCompleteListener (new OnCompleteListener<Void> () {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    if(task.isSuccessful ()){
                                                        Customer customer;
                                                        customer = dataSnapshot.child ("Customer").child (FirebaseAuth.getInstance ().getCurrentUser ().getUid ()).getValue (Customer.class);
                                                        Prevalent.currentOnlineUser = customer;
                                                        Toast.makeText(getApplicationContext(), "Registration Successfull", Toast.LENGTH_LONG).show();
                                                        Intent intent = new Intent(UserRegister.this, NavDrawer.class);
                                                        startActivity(intent);
                                                    }
                                                    else {
                                                        Log.e("ERROR", task.getException().toString());
                                                        Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            });


                                        }

                                    }
                                });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



                //firebase registration of user


            } else {
                Toast.makeText(getApplicationContext(), "Password not match", Toast.LENGTH_LONG).show();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "Invalid Telephone Number", Toast.LENGTH_LONG).show();
        }

    }





}
