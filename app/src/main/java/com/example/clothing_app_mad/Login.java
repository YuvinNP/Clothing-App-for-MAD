package com.example.clothing_app_mad;

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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    private TextView sellerLink, userLink, loginast1, forgotpwrd;
    private Button loginBtn;
    private ProgressDialog progressDialog;
    private LinearLayout loginAs;
    private EditText emailValidate, pwrdValidate;
    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.singupbtn);
        sellerLink = findViewById(R.id.login_as_seller);
        userLink = findViewById(R.id.login_as_user);
        loginAs = findViewById(R.id.loginas);
        loginast1 = findViewById(R.id.loginast1);
        forgotpwrd = findViewById(R.id.forgotpassword);

        emailValidate = findViewById(R.id.emailfield);
        pwrdValidate = findViewById(R.id.passwordfield);

        progressDialog = new ProgressDialog(Login.this);

        mAuth = FirebaseAuth.getInstance();

        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){

                }
            }
        };


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

<<<<<<< HEAD
                userLogin();

//                progressDialog.setTitle("Please Wait");
//                progressDialog.setMessage("You are logging to the system");
//                progressDialog.show();
//                Intent intent = new Intent(Login.this, UserRegister.class);
//                startActivity(intent);
=======
//                progressDialog.setTitle("Please Wait");
//                progressDialog.setMessage("You are logging to the system");
//                progressDialog.show();
                Intent intent = new Intent(Login.this, NavDrawer.class);
                startActivity(intent);
>>>>>>> a7317dfb8875498eed8fab8a8d50b110c10106f1
//                progressDialog.dismiss();


            }
        });

        loginast1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, UserRegister.class);
                startActivity(intent);

            }
        });

       forgotpwrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, ResetPassword.class);
                startActivity(intent);

            }
        });
    }

    public void userLogin(){

        final String uname = emailValidate.getText().toString();
        final String pwrd = pwrdValidate.getText().toString();

        if(!uname.equals("") && !pwrd.equals("")){

            progressDialog.setTitle("User Login");
            progressDialog.setMessage("Please wait, we are checking your credentials");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();




                mAuth.signInWithEmailAndPassword(uname, pwrd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(Login.this, "Signed In", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Login.this, NavDrawer.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(Login.this, "Sign In failed!!!", Toast.LENGTH_LONG).show();
                        }
                    }
                });



        }
        else {

            Toast.makeText(getApplicationContext(), "Please your username of password is missed.", Toast.LENGTH_LONG).show();
        }
    }
}
