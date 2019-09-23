package com.example.clothing_app_mad.Seller;

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
import com.example.clothing_app_mad.Ladies.Ladies_Dresses;
import com.example.clothing_app_mad.R;
import com.example.clothing_app_mad.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class Seller_Product_View_Activity extends AppCompatActivity {

    private DatabaseReference productRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_seller__product__view_ );

        productRef = FirebaseDatabase.getInstance().getReference().child( "Product" );

        recyclerView = findViewById(R.id.recycler_Menu);
        recyclerView.setHasFixedSize( true );
        layoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager( layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerOptions<Product> options =
                new FirebaseRecyclerOptions.Builder<Product>().setQuery( productRef, Product.class).build();

        FirebaseRecyclerAdapter<Product, ProductViewHolder> adapter =
                new FirebaseRecyclerAdapter<Product, ProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull final Product product) {

                        holder.txtProductName.setText( product.getPname() );
                        holder.txtProductPrice.setText( product.getPrice() );
                        holder.txtProductDescription.setText( product.getDescription() );
                        Picasso.get().load( product.getImage() ).into(holder.imageView);

                        holder.itemView.setOnClickListener( new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent intent = new Intent( Seller_Product_View_Activity.this, SellerMaintainProductsActivity.class);
                                intent.putExtra("pid", product.getPid());
                                startActivity(intent);
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
