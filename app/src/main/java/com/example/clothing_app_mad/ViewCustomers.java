package com.example.clothing_app_mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.clothing_app_mad.Entites.Customer;
import com.example.clothing_app_mad.Entites.Product;
import com.example.clothing_app_mad.Prevalent.Prevalent;
import com.example.clothing_app_mad.ViewHolder.CustomerViewHolder;
import com.example.clothing_app_mad.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewCustomers extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Button delete;
    private DatabaseReference dbref;
    ProgressDialog progressDialog;
    private EditText search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_view_customers);

        //search = findViewById (R.id.searchfield);
        recyclerView = findViewById (R.id.customer_list);
        recyclerView.setHasFixedSize (true);
        layoutManager = new LinearLayoutManager (this);
        recyclerView.setLayoutManager (layoutManager);

        dbref = FirebaseDatabase.getInstance ().getReference ().child ("Customer");


        progressDialog = new ProgressDialog (this);

        search.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                usersearch();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart ();

        FirebaseRecyclerOptions<Customer> options =
                new FirebaseRecyclerOptions.Builder<Customer> ()
                        .setQuery (dbref, Customer.class).build ();

        FirebaseRecyclerAdapter<Customer, CustomerViewHolder> adapter = new FirebaseRecyclerAdapter<Customer, CustomerViewHolder> (options) {
            @Override
            protected void onBindViewHolder(@NonNull CustomerViewHolder customerViewHolder, final int position, @NonNull Customer customer) {
                customerViewHolder.cname.setText (customer.getCname ());
                customerViewHolder.email.setText (customer.getEmail ());
                Picasso.get ().load (customer.getImage ()).into (customerViewHolder.imageView);

                customerViewHolder.delete.setOnClickListener (new View.OnClickListener () {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder (ViewCustomers.this);

                        builder.setMessage ("Are you sure to update?")
                                .setPositiveButton ("Yes", new DialogInterface.OnClickListener () {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        progressDialog.setTitle ("Deleteing");
                                        progressDialog.setMessage ("Please, We are Deleting the Customer record");
                                        progressDialog.setCanceledOnTouchOutside (false);
                                        progressDialog.show ();
                                        System.out.println ("ASADASD" + getRef (position).getKey ());
                                        String uID = getRef (position).getKey ();

                                        RemoveCustomer (uID);
                                        progressDialog.dismiss ();

                                        Toast.makeText (ViewCustomers.this, "Successfully Deleted", Toast.LENGTH_SHORT).show ();

                                        startActivity (new Intent (ViewCustomers.this, ViewCustomers.class));
                                    }
                                }).setNegativeButton ("Cancel", null);

                        AlertDialog alertDialog = builder.create ();
                        alertDialog.show ();

                    }
                });

            }

            @NonNull
            @Override
            public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from (parent.getContext ()).inflate (R.layout.customer_components, parent, false);
                CustomerViewHolder holder = new CustomerViewHolder (view);
                return holder;
            }
        };
        recyclerView.setAdapter (adapter);
        adapter.startListening ();

    }

    private void RemoveCustomer(String uID) {


        dbref.child (uID).removeValue ();
    }

    private void usersearch() {



    }
}
