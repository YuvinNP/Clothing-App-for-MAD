package com.example.clothing_app_mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clothing_app_mad.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ConfirmOrderActivity extends AppCompatActivity {

    private EditText nameTxt, phoneTxt, addressTxt, cityTxt;
    private Button confirmOrderBtn;
    private String totalAmount ="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_confirm_order );

        totalAmount = getIntent().getStringExtra("Total Price");
        Toast.makeText(this, "Total Price = Rs." + totalAmount, Toast.LENGTH_SHORT).show();

        confirmOrderBtn = (Button) findViewById(R.id.confirmBtn);
        nameTxt = (EditText) findViewById(R.id.shipmentName);
        phoneTxt = (EditText) findViewById(R.id.shipmentPhone);
        addressTxt = (EditText) findViewById(R.id.shipmentAddress);
        cityTxt = (EditText) findViewById(R.id.shipmentcity);

        confirmOrderBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                check();
            }
        } );
    }
    private void check() {

        if (TextUtils.isEmpty(nameTxt.getText().toString())){

            Toast.makeText(this, "Please Provide Your Full Name...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(phoneTxt.getText().toString())){

            Toast.makeText(this, "Please Provide Your Phone Number...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(addressTxt.getText().toString())){

            Toast.makeText(this, "Please Provide Your Address...", Toast.LENGTH_SHORT).show();
        }
       else if (TextUtils.isEmpty(cityTxt.getText().toString())){

            Toast.makeText(this, "Please Provide Your City...", Toast.LENGTH_SHORT).show();
        }
       else{
           
           ConfirmOrder();
        }
    }

    private void ConfirmOrder()
    {
       final String saveCurrentDate, saveCurrentTime;

        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat( "MMM dd, yyyy" );
        saveCurrentDate = currentDate.format( calForDate.getTime() );

        SimpleDateFormat currentTime = new SimpleDateFormat( "HH:mm:ss a" );
        saveCurrentTime = currentDate.format( calForDate.getTime() );

        final DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference().child( "Orders" )
                .child( Prevalent.currentOnlineUser.getCname() );

        HashMap<String, Object> orderMap = new HashMap<>();

        orderMap.put( "totalAmount", totalAmount);
        orderMap.put( "name", nameTxt.getText().toString() );
        orderMap.put( "phone", phoneTxt.getText().toString() );
        orderMap.put( "address", addressTxt.getText().toString() );
        orderMap.put( "city", cityTxt.getText().toString());
        orderMap.put( "date", saveCurrentDate );
        orderMap.put( "time", saveCurrentTime );
        orderMap.put( "State", "not shipped");

        ordersRef.updateChildren(orderMap).addOnCompleteListener( new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                //to remove items in the cart after user confirm the order
                if (task.isSuccessful()){

                    FirebaseDatabase.getInstance().getReference().child("Cart List")
                            .child("User view").child( Prevalent.currentOnlineUser.getCname() ).removeValue()
                            .addOnCompleteListener( new OnCompleteListener<Void>() {

                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){

                                        Toast.makeText( ConfirmOrderActivity.this, "Your final order has been placed successfully", Toast.LENGTH_SHORT ).show();

                                        Intent intent = new Intent( ConfirmOrderActivity.this, NavDrawer.class );

                                        //to stop the user comming back to the confirm order activity
                                        intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity( intent );
                                        finish();
                                    }
                                }
                            } );
                }
            }
        } );
    }
}
