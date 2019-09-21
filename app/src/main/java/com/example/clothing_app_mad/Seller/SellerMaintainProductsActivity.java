package com.example.clothing_app_mad.Seller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.clothing_app_mad.R;

public class SellerMaintainProductsActivity extends AppCompatActivity {

    private Button productUpdateBtn;
    private EditText name, price, description;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_seller_maintain_products );

        productUpdateBtn = findViewById(R.id.product_update_Btn);
        name = findViewById(R.id.product_name_update);
        price = findViewById(R.id.product_price_update);
        description = findViewById(R.id.product_description_update);
        imageView = findViewById(R.id.product_image_update);

    }
}
