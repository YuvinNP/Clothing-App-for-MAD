package com.example.clothing_app_mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.clothing_app_mad.Entites.Product;
import com.example.clothing_app_mad.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class ProductDetailsActivity extends AppCompatActivity {

    private Button addToCartButton;
    private ElegantNumberButton numberButton;
    private TextView  productName, productDescription, productPrice;
    private ImageView productImage;
    private String productID = "", state = "Normal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productID = getIntent().getStringExtra("pid");

        addToCartButton = (Button) findViewById(R.id.addCartBtn);
        numberButton = (ElegantNumberButton) findViewById(R.id.numberBtn);
        productImage = (ImageView) findViewById(R.id.productImageDetails);
        productName = (TextView) findViewById(R.id.productNameDetails);
        productDescription = (TextView) findViewById(R.id.productDescriptionDetails);
        productPrice = (TextView) findViewById(R.id.productPriceDetails);

        getProductDetails(productID);

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                //validations for the cart to add products
                if (state.equals("Order Placed") || state.equals("Order Shipped")){

                    Toast.makeText(ProductDetailsActivity.this, "you can purchase more products, once your order is shipped or confirmed...", Toast.LENGTH_LONG).show();
                }
                else{
                    addingToCartList();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        checkOrderState();
    }

    private void addingToCartList() {

        String saveCurrentTime, saveCurrentDate;

        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat( "MMM dd, yyyy" );
        saveCurrentDate = currentDate.format( calForDate.getTime() );

        SimpleDateFormat currentTime = new SimpleDateFormat( "HH:mm:ss a" );
        saveCurrentTime = currentDate.format( calForDate.getTime() );

        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child( "Cart List" );

        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put( "pid", productID );
        cartMap.put( "pname", productName.getText().toString() );
        cartMap.put( "price", productPrice.getText().toString() );
        cartMap.put( "date", saveCurrentDate );
        cartMap.put( "time", saveCurrentTime );
        cartMap.put( "quantity", numberButton.getNumber() );
        cartMap.put( "discount", "" );

        cartListRef.child( "User view" ).child( Prevalent.currentOnlineUser.getCname() )
                .child( "Product" ).child( productID )
                .updateChildren(cartMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful()){

                            cartListRef.child( "Seller view" ).child( Prevalent.currentOnlineUser.getCname())
                                    .child( "Product" ).child( productID )
                                    .updateChildren( cartMap )
                                    .addOnCompleteListener( new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText( ProductDetailsActivity.this, "Added to Cart List Successfully", Toast.LENGTH_SHORT ).show();

                                                Intent intent = new Intent(ProductDetailsActivity.this, NavDrawer.class);
                                                startActivity(intent);
                                            }
                                        }
                                    } );
                        }
                    }
                } );
    }

    private void getProductDetails(String ProductID){

        DatabaseReference productRef = FirebaseDatabase.getInstance().getReference().child("Product");

        productRef.child(productID).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){

                    Product product = dataSnapshot.getValue(Product.class);

                    productName.setText(product.getPname());
                    productPrice.setText(product.getPrice ());
                    productDescription.setText(product.getDescription());
                 /*   Picasso.get().load(product.getImage()).into(productImage);*/
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void checkOrderState(){

        DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevalent.currentOnlineUser.getCname());

        orderRef.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String shippingState = dataSnapshot.child("State").getValue().toString();

                if(shippingState.equals("shipped")){

                    state = "Order Shipped";

                }
                else  if(shippingState.equals("not shipped")){

                    state = "Order Placed";

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        } );
    }
}
