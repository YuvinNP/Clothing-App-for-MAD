package com.example.clothing_app_mad.Seller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;

import com.example.clothing_app_mad.R;
import com.example.clothing_app_mad.ViewCustomers;

public class SellerHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView ( R.layout.activity_seller_home);

        GridLayout sellerHomeGrid;

        sellerHomeGrid = findViewById(R.id.sellerHomeGrid);

        setSingleEvent(sellerHomeGrid);

    }

    private void setSingleEvent(GridLayout sellerHomeGrid) {

        //loop all child items of main grid
        for (int i = 0; i < sellerHomeGrid.getChildCount(); i++) {

            CardView cardView = (CardView) sellerHomeGrid.getChildAt( i );

            final int index = i;

            cardView.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (index == 0){

                        Intent intent = new Intent(SellerHome.this, SellerCategoryActivity.class);
                        startActivity(intent);
                    }
                    else if(index == 1){

                        Intent intent = new Intent(SellerHome.this, Seller_Product_View_Activity.class);
                        startActivity(intent);
                    }
                    else if (index == 2){

                        Intent intent = new Intent(SellerHome.this, SellerNewOrdersActivity.class);
                        startActivity(intent);
                    }
                    else if (index == 3){

                        /*Intent intent = new Intent(SellerHome.this, ViewCustomers.class);
                        startActivity(intent);*/
                    }
                    else if (index == 4){

                       /* Intent intent = new Intent(SellerHome.this, SellerAddNewProductActivity.class);
                        startActivity(intent);*/
                    }
                    else if (index == 5){

                       /* Intent intent = new Intent(SellerHome.this, SellerAddNewProductActivity.class);
                        startActivity(intent);*/
                    }
                }
            } );
        }
    }
}
