package com.example.clothing_app_mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MobileAuth extends AppCompatActivity {


    private EditText mobileno, verifycode;
    private Button sendcode, confirm;
    private FirebaseAuth auth;
    String vcode;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_mobile_auth);


        progressDialog = new ProgressDialog (this);

        auth = FirebaseAuth.getInstance ();

        mobileno = findViewById (R.id.mobileno);

        sendcode = findViewById (R.id.buttonverify);
        confirm = findViewById (R.id.buttonconfirm);


        sendcode.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                sendCode ();
            }
        });

        confirm.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                progressDialog.setTitle ("Verifing");
                progressDialog.setMessage ("Your code is verifying");
                progressDialog.setCanceledOnTouchOutside (false);
                progressDialog.show ();
                verifySentCode();

            }
        });

    }

    public void verifySentCode(){
        verifycode = findViewById (R.id.verifycode);
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential (vcode, verifycode.toString ());
        signInWithPhoneAuthCredential (credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult> () {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText (MobileAuth.this, "Code Verified", Toast.LENGTH_SHORT).show ();
                            startActivity (new Intent (MobileAuth.this, ResetPassword.class));
                            progressDialog.dismiss ();
                        } else {

                            if(task.getException () instanceof FirebaseAuthInvalidCredentialsException){
                                Toast.makeText (MobileAuth.this, "Verification Failed", Toast.LENGTH_SHORT).show ();
                                progressDialog.dismiss ();
                            }
                        }
                    }
                });
    }

    private void sendCode(){


        String num = "+94770098395";
        String scode = "123456";
       PhoneAuthProvider.getInstance ().verifyPhoneNumber (scode,60,TimeUnit.SECONDS,MobileAuth.this, mCallbacks);

    }

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks () {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

        }

        @Override
        public void onVerificationFailed(FirebaseException e) {

        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent (s, forceResendingToken);

            vcode = s;
        }
    };


}
