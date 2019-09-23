package com.example.clothing_app_mad;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.clothing_app_mad.Prevalent.Prevalent;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateCustomer extends AppCompatActivity {

    private EditText name, email, contactNo, address1, address2, address3;
    private TextView propic;
    private CircleImageView circleImageView;
    private Button btn, backbtn;
    private Uri imageUri;
    private String url = "";
    private StorageReference propicref;
    private StorageTask upload;
    private String checker = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_customer);

        name = findViewById (R.id.sname);
        email = findViewById (R.id.semail);
        contactNo = findViewById (R.id.contactno);
        address1 = findViewById (R.id.updateAddress1);
        address2 = findViewById (R.id.updateAddress2);
        address3 = findViewById (R.id.updateAddress3);
        circleImageView = findViewById (R.id.userimage);
        btn = findViewById (R.id.updateBtn);
        propic = findViewById (R.id.changepropic);


        backbtn = findViewById (R.id.backBtnR);
        backbtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                startActivity (new Intent (UpdateCustomer.this, NavDrawer.class));
            }
        });

        propicref = FirebaseStorage.getInstance().getReference().child("Customer Images");

        name.setText (Prevalent.currentOnlineUser.getCname ());
        email.setText (Prevalent.currentOnlineUser.getEmail ());
        System.out.println (Prevalent.currentOnlineUser.getContactNo ());
        contactNo.setText (""+Prevalent.currentOnlineUser.getContactNo ());
        address1.setText (Prevalent.currentOnlineUser.getAddressLine1 ());
        address2.setText (Prevalent.currentOnlineUser.getAddressLine2 ());
        address3.setText (Prevalent.currentOnlineUser.getDistrict ());

        if(Prevalent.currentOnlineUser.getImage ()!=null){
            Picasso.get ().load (Prevalent.currentOnlineUser.getImage ()).into (circleImageView);
        }


        btn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder (UpdateCustomer.this);

                builder.setMessage ("Are you sure to update?")
                        .setPositiveButton ("Yes", new DialogInterface.OnClickListener () {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(checker.equals ("clicked")){

                                    userinfoUpdate ();
                                }
                                else {

                                    updateWithoutImage ();
                                }
                            }
                        }).setNegativeButton ("Cancel", null);

                AlertDialog alertDialog = builder.create ();
                alertDialog.show ();




            }
        });

        propic.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                checker = "clicked";
                CropImage.activity (imageUri).setAspectRatio (1,1)
                        .start (UpdateCustomer.this);
            }
        });
    }

    public void updateWithoutImage(){

        System.out.println ("Update Image");
        final ProgressDialog progressDialog = new ProgressDialog (this);
        progressDialog.setTitle ("Update Profile");
        progressDialog.setMessage ("Please wait, we are updating your profile");
        progressDialog.setCanceledOnTouchOutside (false);
        progressDialog.show ();






        DatabaseReference dbref;
        dbref = FirebaseDatabase.getInstance ().getReference ();

        dbref.addListenerForSingleValueEvent (new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                DatabaseReference ref = FirebaseDatabase.getInstance ().getReference ().child ("Customer");

                HashMap<String, Object> usermap = new HashMap<> ();
                usermap.put ("cname", name.getText ().toString ());
                usermap.put ("email", email.getText ().toString ());
                usermap.put ("contactNo", Integer.parseInt (contactNo.getText ().toString ()));
                usermap.put ("addressLine1", address1.getText ().toString ());
                usermap.put ("addressLine2", address2.getText ().toString ());
                usermap.put ("district", address3.getText ().toString ());
                ref.child (FirebaseAuth.getInstance ().getCurrentUser ().getUid ()).updateChildren (usermap);

                Prevalent.currentOnlineUser.setCname (name.getText ().toString ());
                Prevalent.currentOnlineUser.setEmail ( email.getText ().toString ());
                Prevalent.currentOnlineUser.setContactNo (Integer.parseInt (contactNo.getText ().toString ()));
                Prevalent.currentOnlineUser.setAddressLine1 (address1.getText ().toString ());
                Prevalent.currentOnlineUser.setAddressLine2 (address2.getText ().toString ());
                Prevalent.currentOnlineUser.setDistrict (address3.getText ().toString ());

                progressDialog.dismiss ();
                startActivity (new Intent (UpdateCustomer.this, NavDrawer.class));
                Toast.makeText (UpdateCustomer.this, "Updated Successfull", Toast.LENGTH_SHORT).show ();
                finish ();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult (requestCode, resultCode, data);

        if(requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode==RESULT_OK && data!=null){

            CropImage.ActivityResult result = CropImage.getActivityResult (data);
            imageUri = result.getUri ();

            circleImageView.setImageURI (imageUri);
        }
        else{
            Toast.makeText (this, "Error try again", Toast.LENGTH_SHORT).show ();

            startActivity (new Intent (UpdateCustomer.this, UpdateCustomer.class));
        }
    }

    public void userinfoUpdate(){

        System.out.println ("HEllo Ysasd");
        if(TextUtils.isEmpty (name.getText ().toString ())){
            Toast.makeText (this, "Name is Emplty", Toast.LENGTH_SHORT).show ();
        }
        else if(TextUtils.isEmpty (email.getText ().toString ())){
            Toast.makeText (this, "Email is Emplty", Toast.LENGTH_SHORT).show ();
        }
        else if(TextUtils.isEmpty (address1.getText ().toString ())){
            Toast.makeText (this, "Address is Empty", Toast.LENGTH_SHORT).show ();
        }
        else if(checker.equals ("clicked")){

            uploadImage ();
        }

    }

    public void uploadImage(){

        System.out.println ("Update Image");
        final ProgressDialog progressDialog = new ProgressDialog (this);
        progressDialog.setTitle ("Update Profile");
        progressDialog.setMessage ("Please wait, we are updating your profile");
        progressDialog.setCanceledOnTouchOutside (false);
        progressDialog.show ();

        if(imageUri!=null){
            final StorageReference storageReference = propicref.child (Prevalent.currentOnlineUser.getCustomerID () + ".jpg");

            upload = storageReference.putFile (imageUri);

            upload.continueWithTask (new Continuation () {
                @Override
                public Object then(@NonNull Task task) throws Exception {

                    if(!task.isSuccessful ()){
                        throw task.getException ();
                    }
                    return storageReference.getDownloadUrl ();
                }

            }).addOnCompleteListener (new OnCompleteListener () {
                @Override
                public void onComplete(@NonNull Task task) {
                    if(task.isSuccessful ()){

                        System.out.println ("Here");
                        Uri uri = (Uri)task.getResult ();
                        url = uri.toString ();

                        DatabaseReference ref = FirebaseDatabase.getInstance ().getReference ().child ("Customer");

                        HashMap<String, Object> usermap = new HashMap<String, Object> ();
                        usermap.put ("cname", name.getText ().toString ());
                        usermap.put ("email", email.getText ().toString ());
                      //  usermap.put ("contactNo", contactNo.getText ().toString ());
                        usermap.put ("addressLine1", address1.getText ().toString ());
                        usermap.put ("addressLine2", address2.getText ().toString ());
                        usermap.put ("district", address3.getText ().toString ());
                        usermap.put ("image", url);

                        ref.child (Prevalent.currentOnlineUser.getCustomerID ()).updateChildren (usermap);
                        Prevalent.currentOnlineUser.setCname (name.getText ().toString ());
                        Prevalent.currentOnlineUser.setEmail ( email.getText ().toString ());
//                Prevalent.currentOnlineUser.setContactNo (Integer.parseInt (contactNo.getText ().toString ()));
                        Prevalent.currentOnlineUser.setAddressLine1 (address1.getText ().toString ());
                        Prevalent.currentOnlineUser.setAddressLine2 (address2.getText ().toString ());
                        Prevalent.currentOnlineUser.setDistrict (address3.getText ().toString ());
                        Prevalent.currentOnlineUser.setImage (url);

                        progressDialog.dismiss ();
                        startActivity (new Intent (UpdateCustomer.this, NavDrawer.class));
                        Toast.makeText (UpdateCustomer.this, "Updated Successfull", Toast.LENGTH_SHORT).show ();
                    }
                    else {
                        progressDialog.dismiss ();

                        Toast.makeText (UpdateCustomer.this, "Update Failed", Toast.LENGTH_SHORT).show ();
                    }
                }
            });

        }
    }

}
