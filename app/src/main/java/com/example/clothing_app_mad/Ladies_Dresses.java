package com.example.clothing_app_mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.clothing_app_mad.Entites.Product;
import com.example.clothing_app_mad.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class Ladies_Dresses extends AppCompatActivity {

    Query query;
    private DatabaseReference productRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_ladies__dresses );


       /* productRef = FirebaseDatabase.getInstance().getReference().child( "Product" );*/

        query = FirebaseDatabase.getInstance().getReference("Product").orderByChild("category").equalTo("WomenDresses");

        recyclerView = findViewById(R.id.recycler_Menu_LadiesDresses);
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
                    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int i, @NonNull Product product) {

                        holder.txtProductName.setText( product.getPname() );
                        holder.txtProductPrice.setText( product.getPrice() );
                        holder.txtProductDescription.setText( product.getDescription() );
                        Picasso.get().load( product.getImage() ).into(holder.imageView);

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
