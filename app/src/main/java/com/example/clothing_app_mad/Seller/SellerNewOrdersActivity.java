package com.example.clothing_app_mad.Seller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.clothing_app_mad.Entites.SellerOrders;
import com.example.clothing_app_mad.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SellerNewOrdersActivity extends AppCompatActivity {

    //Recycler view in the SellerNewOrdersActivity
    private RecyclerView orderList;
    private DatabaseReference ordersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_seller_new_orders );



        ordersRef = FirebaseDatabase.getInstance().getReference().child( "Orders" );

        orderList = findViewById( R.id.order_list );
        orderList.setLayoutManager( new LinearLayoutManager(this) );
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<SellerOrders> options =
                new FirebaseRecyclerOptions.Builder<SellerOrders>()
                .setQuery(ordersRef, SellerOrders.class).build();

        FirebaseRecyclerAdapter<SellerOrders, SellerOrdersViewHolder> adapter =
                new FirebaseRecyclerAdapter<SellerOrders, SellerOrdersViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull SellerOrdersViewHolder holder, final int position, @NonNull final SellerOrders sellerOrders) {

                        holder.customerName.setText(sellerOrders.getName());
                        holder.customerPhoneNo.setText(sellerOrders.getPhone());
                        holder.customerShippingAddress.setText(sellerOrders.getAddress() + sellerOrders.getCity());
                       // holder.customerTotalPrice.setText( sellerOrders.getTotalPrice() );
                        holder.customerDateTime.setText(sellerOrders.getDate() + sellerOrders.getTime());

                        holder.showOrdersBtn.setOnClickListener( new View.OnClickListener() {
                            @Override
                            public void onClick(View view)
                            {
                                String uID = getRef(position).getKey();

                                Intent intent = new Intent(SellerNewOrdersActivity.this, Seller_User_order_Product_Activity.class);
                                intent.putExtra("uid", uID);
                                startActivity(intent);

                            }
                        } );

                        //a dialogue box to check whether the order is shipped or not
                        holder.itemView.setOnClickListener( new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                CharSequence options[] = new CharSequence[]{

                                            "Yes",
                                            "No"
                                };
                                AlertDialog.Builder builder = new AlertDialog.Builder(SellerNewOrdersActivity.this);
                                builder.setTitle("Have ypu shipped this products ? ");

                                builder.setItems( options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {

                                        if (i == 0){

                                            //if seller confirm the shipment order that order will remove from the database
                                            String uID = getRef(position).getKey();
                                            RemoveOrder(uID);
                                        }
                                        else{
                                            finish();
                                        }
                                    }
                                } );
                                //to call the dialogue box
                                builder.show();
                            }
                        } );
                    }

                    @NonNull
                    @Override
                    public SellerOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                        //accessing the seller_order layout
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.seller_orders, parent, false);
                        return new SellerOrdersViewHolder(view);
                    }
                };

            orderList.setAdapter(adapter);
            adapter.startListening();
    }



    public static class SellerOrdersViewHolder extends RecyclerView.ViewHolder{

        public TextView customerName, customerPhoneNo, customerTotalPrice, customerShippingAddress, customerDateTime;
        public Button showOrdersBtn;

        public SellerOrdersViewHolder(@NonNull View itemView) {
            super( itemView );

            customerName = itemView.findViewById(R.id.order_cutomerName);
            customerPhoneNo = itemView.findViewById(R.id.order_phoneN0);
            customerTotalPrice = itemView.findViewById(R.id.order_total_price);
            customerShippingAddress = itemView.findViewById(R.id.order_address);
            customerDateTime = itemView.findViewById(R.id.order_date_time);

            showOrdersBtn = itemView.findViewById(R.id.show_allproduct_btn);

        }
    }

    //to remove order from the database after shipped
    private void RemoveOrder(String uID)
    {
        ordersRef.child(uID).removeValue();
    }
}
