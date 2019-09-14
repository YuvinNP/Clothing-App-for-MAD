package com.example.clothing_app_mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clothing_app_mad.Entites.Cart;
import com.example.clothing_app_mad.Prevalent.Prevalent;
import com.example.clothing_app_mad.ViewHolder.CartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button nextbutton;
    private TextView txtTotalAmount, txtCartMsg1;

   private int overallTotalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_cart );

        recyclerView = findViewById(R.id.cart_list);
        recyclerView.setHasFixedSize( true );
        layoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager( layoutManager );

        nextbutton = (Button) findViewById(R.id.nextBtn);
        txtTotalAmount = (TextView) findViewById(R.id.total_price);
        txtCartMsg1 = (TextView) findViewById(R.id.cartMsg1);

        nextbutton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CartActivity.this, ConfirmOrderActivity.class);
                intent.putExtra("Total Price ", String.valueOf(overallTotalPrice));
                startActivity(intent);
                finish();

            }
        } );
    }

    @Override
    protected void onStart() {
        super.onStart();

        //call the method
        //checkOrderState();

        //to preview total price in the cart activity itself
        txtTotalAmount.setText(String.valueOf(overallTotalPrice));

        //to retrive the data on the cart list table
        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");

        FirebaseRecyclerOptions<Cart> options =
                new FirebaseRecyclerOptions.Builder<Cart>()
                .setQuery(cartListRef.child("User view")
                        .child( Prevalent.currentOnlineUser.getCname()).child("Product"), Cart.class).build();

        FirebaseRecyclerAdapter<Cart, CartViewHolder> adapter = new FirebaseRecyclerAdapter<Cart, CartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull CartViewHolder holder, int position, @NonNull final Cart cart) {

                holder.txtProductName.setText(cart.getPname() );
                holder.txtProductPrice.setText(cart.getPrice());
                holder.txtProductQty.setText(cart.getQuantity());
               /* Picasso.get().load (cart.getImage()).into(holder.imageView);*/

                int oneProductTotalPrice = (Integer.valueOf(cart.getPrice())) * (Integer.valueOf(cart.getQuantity()));

                overallTotalPrice = overallTotalPrice + oneProductTotalPrice;

                //to remove or edit a item in the cart
             holder.itemView.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                       CharSequence options[] = new CharSequence[]{

                                "Edit",
                                "Remove"
                        };
                        AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);
                        builder.setTitle("Cart Options:");

                        builder.setItems( options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                if(i == 0)
                                {
                                    Intent intent = new Intent(CartActivity.this, ProductDetailsActivity.class);
                                    intent.putExtra("pid", cart.getPid());
                                    startActivity(intent);
                                }
                                if(i == 1)
                                {
                                    cartListRef.child("User view").child(Prevalent.currentOnlineUser.getCname())
                                            .child("Product").child(cart.getPid()).removeValue().addOnCompleteListener( new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful())
                                                {
                                                    Toast.makeText(CartActivity.this, "Item removed successfully", Toast.LENGTH_SHORT).show();

                                                    Intent intent = new Intent(CartActivity.this, CartActivity.class);
                                                    startActivity(intent);
                                                }
                                        }
                                    } );
                                }
                            }
                        } );
                        //to show the alert dialogue
                        builder.show();
                    }
                } );
            }

            @NonNull
            @Override
            public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item_layout, parent, false);
                CartViewHolder holder = new CartViewHolder( view );
                return holder;

            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    //to check the shipping order state
   /* private void  checkOrderState(){

        DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevalent.currentOnlineUser.getCname());

        orderRef.addValueEventListener( new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String shippingState = dataSnapshot.child("State").getValue().toString();
                String username = dataSnapshot.child("name").getValue().toString();

                if(shippingState.equals("shipped")){

                    txtTotalAmount.setText("Dear " + username + ",\n order is shipped successfully..." );
                    recyclerView.setVisibility(View.GONE);

                    txtCartMsg1.setVisibility( View.VISIBLE );
                    txtCartMsg1.setText("Congradulations, your final order has been shipped successfully... Soon you will received your order at your door step...");

                    nextbutton.setVisibility(View.GONE);


                    //msg for the users about order
                    Toast.makeText( CartActivity.this, "You can purchase more products, once you received your first final order...", Toast.LENGTH_LONG ).show();


                }
                else  if(shippingState.equals("not shipped")){

                    txtTotalAmount.setText("Shipping State = Not Shipped");
                    recyclerView.setVisibility(View.GONE);

                    txtCartMsg1.setVisibility( View.VISIBLE );
                    nextbutton.setVisibility(View.GONE);

                    Toast.makeText( CartActivity.this, "You can purchase more products, once you received your first final order...", Toast.LENGTH_LONG ).show();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        } );
    }*/
}
