package com.example.clothing_app_mad.Gents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clothing_app_mad.Entites.Product;
import com.example.clothing_app_mad.Prevalent.Prevalent;
import com.example.clothing_app_mad.ProductDetailsActivity;
import com.example.clothing_app_mad.R;
import com.example.clothing_app_mad.Seller.SellerMaintainProductsActivity;
import com.example.clothing_app_mad.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class Gents_TShirts extends AppCompatActivity {

    Query query;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_gents__tshirts );

        query = FirebaseDatabase.getInstance().getReference("Product").orderByChild("category").equalTo("GentsTshirts");

        recyclerView = findViewById(R.id.recycler_Menu);
        recyclerView.setHasFixedSize( true );
        layoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager( layoutManager );
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Product> options =
                new FirebaseRecyclerOptions.Builder<Product>().setQuery( query, Product.class)
                        .build();

        FirebaseRecyclerAdapter<Product, ProductViewHolder> adapter =
                new FirebaseRecyclerAdapter<Product, ProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int i, @NonNull final Product product) {

                        holder.txtProductName.setText( product.getPname() );
                        holder.txtProductPrice.setText( product.getPrice() );
                        holder.txtProductDescription.setText( product.getDescription() );
                        Picasso.get().load( product.getImage() ).into(holder.imageView);

                        holder.itemView.setOnClickListener( new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                //if it is seller, seller direct to update details
                                if (Prevalent.currentOnlineUser == null){

                                    Intent intent = new Intent( Gents_TShirts.this, SellerMaintainProductsActivity.class);
                                    intent.putExtra("pid", product.getPid());
                                    startActivity(intent);
                                }
                                //if it is customer, customer direct to product details activity
                                else{
                                    Intent intent = new Intent(Gents_TShirts.this, ProductDetailsActivity.class);
                                    intent.putExtra("pid", product.getPid());
                                    startActivity(intent);
                                }

                            }
                        } );
                    }

                    @NonNull
                    @Override
                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        View view = LayoutInflater.from( parent.getContext() ).inflate(R.layout.product_items, parent, false);

                        ProductViewHolder holder = new ProductViewHolder( view );
                        return holder;
                    }
                };

        recyclerView.setAdapter( adapter );
        adapter.startListening();
    }
}
