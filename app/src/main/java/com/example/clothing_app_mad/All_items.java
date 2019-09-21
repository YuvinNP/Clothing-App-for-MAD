package com.example.clothing_app_mad;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.clothing_app_mad.Entites.Product;
import com.example.clothing_app_mad.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class All_items extends Fragment {

    private All_items.OnFragmentInteractionListener mListener;

    private DatabaseReference productRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*return super.onCreateView(inflater, container, savedInstanceState);*/

        productRef = FirebaseDatabase.getInstance ().getReference ().child ("Product");
        View view = inflater.inflate(R.layout.fragment_all_items, container, false);

        recyclerView = view.findViewById(R.id.recycler_Menu_All);

        recyclerView.setHasFixedSize (true);
        layoutManager = new LinearLayoutManager (this.getActivity ());
        recyclerView.setLayoutManager (layoutManager);



       /* PagerAdapter pagerAdapter = new PagerAdapter();
        recyclerView.setAdapter(pagerAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
*/
        return view;
    }

    @Override
    public void onStart() {
        super.onStart ();

        FirebaseRecyclerOptions<Product> options = new FirebaseRecyclerOptions.Builder<Product> ().setQuery (productRef, Product.class).build ();

        FirebaseRecyclerAdapter<Product, ProductViewHolder> adapter = new FirebaseRecyclerAdapter<Product, ProductViewHolder> (options) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i, @NonNull final Product product) {

                productViewHolder.txtProductName.setText (product.getPname ());
                productViewHolder.txtProductDescription.setText (product.getDescription ());
                productViewHolder.txtProductPrice.setText (product.getPrice ());
                Picasso.get ().load (product.getImage ()).into (productViewHolder.imageView);


                productViewHolder.itemView.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
                        intent.putExtra("pid", product.getPid());
                        startActivity(intent);
                    }
                } );
            }

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.product_items, parent, false);
                ProductViewHolder holder = new ProductViewHolder (view);
                return holder;
            }
        };
        recyclerView.setAdapter (adapter);
        adapter.startListening ();
    }
}
