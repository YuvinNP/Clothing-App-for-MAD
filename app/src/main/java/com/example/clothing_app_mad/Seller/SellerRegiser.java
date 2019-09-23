package com.example.clothing_app_mad.Seller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clothing_app_mad.Entites.Seller;
import com.example.clothing_app_mad.Login;
import com.example.clothing_app_mad.MainActivity;
import com.example.clothing_app_mad.NavDrawer;
import com.example.clothing_app_mad.Prevalent.Prevalent;
import com.example.clothing_app_mad.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SellerRegiser extends AppCompatActivity {

    private EditText name, email, address1, address2, address3, contact, confirmP, createP;
    Button sregBtn,backbtn;
    TextView login1, login2;
    DatabaseReference dbref;
    Seller seller;
    ProgressDialog progressbar;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView ( R.layout.activity_seller_regiser);

        name = findViewById (R.id.sname);
        email = findViewById (R.id.semail);
        contact = findViewById (R.id.contactNo);
        address1 = findViewById (R.id.saddress1);
        address2 = findViewById (R.id.saddress2);
        address3 = findViewById (R.id.address3);
        createP = findViewById (R.id.crpwrd);
        confirmP = findViewById (R.id.confpwrd);
        sregBtn = findViewById (R.id.signupbtn);
        login1 = findViewById (R.id.textView8);
        login2 = findViewById (R.id.textView9);
        backbtn = findViewById (R.id.backBtnR);

        firebaseAuth = FirebaseAuth.getInstance();
        progressbar = new ProgressDialog (this);


        sregBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                sellerRegister ();
            }
        });

        backbtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                startActivity (new Intent (SellerRegiser.this, MainActivity.class));

            }
        });

        login1.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity (new Intent (SellerRegiser.this,Login.class));
            }
        });

        login2.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity (new Intent (SellerRegiser.this,Login.class));
            }
        });
    }



    public void sellerRegister(){

        final String sname = name.getText ().toString ();
        final String semail = email.getText ().toString ();
        final int contactno = Integer.parseInt (contact.getText ().toString ());
        final String addLine1 = address1.getText ().toString ();
        final String addLine2 = address2.getText ().toString ();
        final String district = address3.getText ().toString ();
        final String createPassword = createP.getText ().toString ();
        final String confirmPassword = confirmP.getText ().toString ();

        dbref = FirebaseDatabase.getInstance().getReference().child("Seller");

        try {

            if (TextUtils.isEmpty (sname) ){
                Toast.makeText (getApplicationContext (), "Please Enter the name", Toast.LENGTH_SHORT).show ();
            } else if (TextUtils.isEmpty (semail)) {
                Toast.makeText (getApplicationContext (), "Enter the email", Toast.LENGTH_LONG).show ();
            } else if (TextUtils.isEmpty (contact.getText ().toString ())) {
                Toast.makeText (getApplicationContext (), "Enter Contact no", Toast.LENGTH_LONG).show ();
            } else if (TextUtils.isEmpty (createPassword)) {
                Toast.makeText (getApplicationContext (), "Enter Your Password", Toast.LENGTH_LONG);

            } else if (TextUtils.isEmpty (confirmPassword)) {

                Toast.makeText (getApplicationContext (), "Confirm Your Password", Toast.LENGTH_LONG).show ();
            } else if (TextUtils.isEmpty (addLine1)){

                Toast.makeText (getApplicationContext (), "Fill Address Line 1", Toast.LENGTH_LONG).show ();

            } else if (TextUtils.isEmpty (addLine2)) {

                Toast.makeText (getApplicationContext (), "Fill Address Line 2", Toast.LENGTH_LONG).show ();

            }else if(TextUtils.isEmpty (district)){

                Toast.makeText (getApplicationContext (), "Enter District", Toast.LENGTH_LONG).show ();

            }else if (createP.getText().toString().equals(confirmP.getText().toString())) {


                progressbar.setTitle ("Seller Registration");
                progressbar.setMessage("Please wait, you are registering to the system");
                progressbar.setCanceledOnTouchOutside(false);
                progressbar.show();

                DatabaseReference dbref;
                dbref = FirebaseDatabase.getInstance ().getReference ();

                dbref.addListenerForSingleValueEvent (new ValueEventListener () {
                    @Override
                    public void onDataChange(final DataSnapshot dataSnapshot) {

                        (firebaseAuth.createUserWithEmailAndPassword(semail, confirmPassword))
                                .addOnCompleteListener(new OnCompleteListener<AuthResult> () {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        progressbar.dismiss();

                                        if(task.isSuccessful()){


                                           Seller s = new Seller (
                                                    sname,
                                                    semail,
                                                    contactno,
                                                    addLine1,
                                                    addLine2,
                                                    district,
                                                    confirmPassword

                                            );
                                            System.out.println ("asasdasd"+FirebaseDatabase.getInstance ().getReference ("Seller").child (FirebaseAuth.getInstance ().getCurrentUser ().getUid()));

                                            FirebaseDatabase.getInstance ().getReference ("Seller").child (FirebaseAuth.getInstance ().getCurrentUser ().getUid ())
                                                    .setValue (s).addOnCompleteListener (new OnCompleteListener<Void> () {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    if(task.isSuccessful ()){

                                                        seller = dataSnapshot.child ("Seller").child (FirebaseAuth.getInstance ().getCurrentUser ().getUid ()).getValue (Seller.class);
                                                        Prevalent.currentOnlineSeller = seller;
                                                        Toast.makeText(getApplicationContext(), "Registration Successfull", Toast.LENGTH_LONG).show();
                                                        Intent intent = new Intent(SellerRegiser.this, Login.class);
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
