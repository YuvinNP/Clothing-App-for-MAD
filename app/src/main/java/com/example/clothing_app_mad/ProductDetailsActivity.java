package com.example.clothing_app_mad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.clothing_app_mad.Entites.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProductDetailsActivity extends AppCompatActivity {

    //private FloatingActionButton addToCartBtn;
    private ImageView productImage;
    private ElegantNumberButton numberButton;
    private TextView  productName, productDescription, productPrice;
    private String productID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productID = getIntent().getStringExtra("pid");

        //addToCartBtn = (FloatingActionButton) findViewById(R.id.addProductCartBtn);
        numberButton = (ElegantNumberButton) findViewById(R.id.numberBtn);
        productImage = (ImageView) findViewById(R.id.productImageDetails);
        productName = (TextView) findViewById(R.id.productNameDetails);
        productDescription = (TextView) findViewById(R.id.productDescriptionDetails);
        productPrice = (TextView) findViewById(R.id.productPriceDetails);

        getProductDetails(productID);
    }
    private void getProductDetails(String ProductID){

        DatabaseReference productRef = FirebaseDatabase.getInstance().getReference().child("Product");
        productRef.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){

                    Product product = dataSnapshot.getValue(Product.class);

                    productName.setText(product.getPname());
                    productPrice.setText(product.getPrice());
                    productDescription.setText(product.getDescription());
                  //  Picasso.get().load(product.getImage().into(productImage));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
