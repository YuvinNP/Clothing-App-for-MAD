package com.example.clothing_app_mad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.clothing_app_mad.Prevalent.Prevalent;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class SettingActivity extends AppCompatActivity {

   /* private CircleImageView profileImageView;
    private EditText fullNameEditTxt, phoneNumEditTxt, addressEditTxt;
    private TextView profileChangeTxtBtn, closeTxtBtn, saveTxtBtn;*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

       /* profileImageView = (CircleImageView) findViewById(R.id.settingProfileImage);
        fullNameEditTxt = (EditText) findViewById(R.id.settingFullName);
        phoneNumEditTxt = (EditText) findViewById(R.id.settingPhoneNumber);
        addressEditTxt = (EditText) findViewById(R.id.settingAddress);
        profileChangeTxtBtn = (TextView) findViewById(R.id.settingProfileImage);
        closeTxtBtn = (TextView) findViewById(R.id.closeSettingBtn);
        saveTxtBtn = (TextView) findViewById(R.id.updateSettingBtn);

        userInfoDisplay(profileImageView, fullNameEditTxt, phoneNumEditTxt, addressEditTxt);*/
    }
 /*   private void userInfoDisplay(CircleImageView profileImageView, EditText fullNameEditTxt, EditText phoneNumEditTxt, EditText addressEditTxt)  {

        DatabaseReference UserRef = FirebaseDatabase.getInstance().getReference().child("Customer").child(Prevalent.currentOnlineUser.getEmail());

        UserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){

                    if (dataSnapshot.child("image").exists()){

                        String image = dataSnapshot.child("image").getValue().toString();
                        String name = dataSnapshot.child("cname").getValue().toString();
                        String pass = dataSnapshot.child("image").getValue().toString();
                        String address = dataSnapshot.child("image").getValue().toString();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/
}
