package com.example.clothing_app_mad.Seller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.clothing_app_mad.R;

public class SellerCategoryActivity extends AppCompatActivity {

    private ImageView WomenDresses, WomenSkirts, WomenTops, WomenTrousers;
    private ImageView GentsShirts, GentsTrousers, GentsTshirts, GentsShorts;
    private ImageView Girls, Boys;
    private ImageView BoysAccessories, GirlsAccessories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_seller_category );

        WomenDresses = (ImageView) findViewById(R.id.female_dresses);
        WomenSkirts = (ImageView) findViewById(R.id.female_skirts);
        WomenTops = (ImageView) findViewById(R.id.female_Tops);
        WomenTrousers = (ImageView) findViewById(R.id.female_trousers);

        GentsShirts = (ImageView) findViewById(R.id.gents_shirts);
        GentsTrousers = (ImageView) findViewById(R.id.gents_trousers);
        GentsTshirts = (ImageView) findViewById(R.id.gents_tshirts);
        GentsShorts = (ImageView) findViewById(R.id.gents_shorts);

        Girls = (ImageView) findViewById(R.id.girl_Items);
        Boys = (ImageView) findViewById(R.id.boy_Items);
        GirlsAccessories = (ImageView) findViewById(R.id.girl_accessories);
        BoysAccessories = (ImageView) findViewById(R.id.boy_accessories);

        WomenDresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( SellerCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category","WomenDresses");
                startActivity(intent);
            }
        });

        WomenSkirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( SellerCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category","WomenSkirts");
                startActivity(intent);
            }
        });

        WomenTops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( SellerCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category","WomenTops");
                startActivity(intent);
            }
        });

        WomenTrousers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( SellerCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category","WomenTrousers");
                startActivity(intent);
            }
        });

        GentsShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( SellerCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category","GentsShirts");
                startActivity(intent);
            }
        });

        GentsTrousers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( SellerCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category","GentsTrousers");
                startActivity(intent);
            }
        });

        GentsTshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( SellerCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category","GentsTshirts");
                startActivity(intent);
            }
        });

        GentsShorts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( SellerCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category","GentsShorts");
                startActivity(intent);
            }
        });

        Girls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( SellerCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category","GirlsItems");
                startActivity(intent);
            }
        });

        Boys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( SellerCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category","BoysItems");
                startActivity(intent);
            }
        });

        GirlsAccessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( SellerCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category","GirlsAccessories");
                startActivity(intent);
            }
        });

        BoysAccessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent( SellerCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("category","BoysAccessories");
                startActivity(intent);
            }
        });
    }
}