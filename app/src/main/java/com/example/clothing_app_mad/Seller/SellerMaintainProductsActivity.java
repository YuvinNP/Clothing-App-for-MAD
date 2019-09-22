package com.example.clothing_app_mad.Seller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.clothing_app_mad.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class SellerMaintainProductsActivity extends AppCompatActivity {

    private Button productUpdateBtn, productDeleteBtn;
    private EditText name, price, description;
    private ImageView imageView;

    private String productID = "";
    private DatabaseReference productRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_seller_maintain_products );

        //to retrive the data to the update form
        productID = getIntent().getStringExtra("pid");
        productRef = FirebaseDatabase.getInstance().getReference().child( "Product" ).child(productID);

        productUpdateBtn = findViewById(R.id.product_update_btn);
        productDeleteBtn = findViewById( R.id.product_delete_btn );
        name = findViewById(R.id.product_name_update);
        price = findViewById(R.id.product_price_update);
        description = findViewById(R.id.product_description_update);
        imageView = findViewById(R.id.product_image_update);

        displaySelectProductInfo();

        productUpdateBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                applyChanges();
            }
        } );

        productDeleteBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteProduct();
            }
        } );
    }

    private void deleteProduct() {

        productRef.removeValue().addOnCompleteListener( new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                Toast.makeText( SellerMaintainProductsActivity.this, "The product is deleted successfully...", Toast.LENGTH_LONG ).show();

              /*  Intent intent = new Intent(SellerMaintainProductsActivity.this, SellerCategoryActivity.class);
                startActivity(intent);
                finish(); */
            }
        } );
    }


    private void applyChanges(){

        String pName = name.getText().toString();
        String pPrice = price.getText().toString();
        String pDescription = description.getText().toString();

        if (pName.equals("")){

            Toast.makeText( this,"Enter the product name...", Toast.LENGTH_LONG).show();
        }
        else if (pPrice.equals("")){

            Toast.makeText( this,"Enter the product price...", Toast.LENGTH_LONG).show();
        }
        else if (pDescription.equals("")){

            Toast.makeText( this,"Enter the product description...", Toast.LENGTH_LONG).show();
        }
        else {

            HashMap<String, Object> productMap = new HashMap<>();
            productMap.put("pid", productID);
            productMap.put("pname", pName);
            productMap.put("description", pDescription);
            productMap.put("price", pPrice);

            productRef.updateChildren(productMap).addOnCompleteListener( new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()){

                        Toast.makeText(SellerMaintainProductsActivity.this, "Product Details Updated Successfully...", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(SellerMaintainProductsActivity.this, SellerCategoryActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            } );
        }
    }

    //to retrive the data to the update form
    private void displaySelectProductInfo(){

        productRef.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists()){

                    String pName = dataSnapshot.child("pname").getValue().toString();
                    String pPrice = dataSnapshot.child("price").getValue().toString();
                    String pDescription = dataSnapshot.child("description").getValue().toString();
                    String pImage = dataSnapshot.child("image").getValue().toString();

                    name.setText(pName);
                    price.setText(pPrice);
                    description.setText(pDescription);
                    Picasso.get().load(pImage).into(imageView);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        } );
    }
}
