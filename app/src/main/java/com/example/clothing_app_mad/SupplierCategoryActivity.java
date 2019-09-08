package com.example.clothing_app_mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SupplierCategoryActivity extends AppCompatActivity {

    private ImageView WomenDresses, WomenSkirts, WomenBlouses, WomenTrousers;
    private ImageView GentsShirts, GentsTrousers, GentsTshirts;
    private ImageView GirlDresses, GirlSkirts, GirlTshirts;
    private ImageView BoyTrousers, BoyTshirts;
    private ImageView hats_caps, hairAccessories, socks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_category);

        WomenDresses = (ImageView) findViewById(R.id.female_dresses);
        WomenSkirts = (ImageView) findViewById(R.id.female_skirts);
        WomenBlouses = (ImageView) findViewById(R.id.female_blouses);
        WomenTrousers = (ImageView) findViewById(R.id.female_trousers);

        GentsShirts = (ImageView) findViewById(R.id.gents_shirts);
        GentsTrousers = (ImageView) findViewById(R.id.gents_trousers);
        GentsTshirts = (ImageView) findViewById(R.id.gents_tshirts);

        GirlDresses = (ImageView) findViewById(R.id.girl_dresses);
        GirlSkirts = (ImageView) findViewById(R.id.girl_skirts);
        GirlTshirts = (ImageView) findViewById(R.id.girl_tshirts);

        BoyTrousers = (ImageView) findViewById(R.id.boy_trousers);
        BoyTshirts = (ImageView) findViewById(R.id.boy_tshirts);

        hats_caps = (ImageView) findViewById(R.id.hatsCaps);
        hairAccessories = (ImageView) findViewById(R.id.hair_accessories);
        socks  = (ImageView) findViewById(R.id.socks);

        WomenDresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","WomenDresses");
                startActivity(intent);
            }
        });

        WomenSkirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","WomenSkirts");
                startActivity(intent);
            }
        });

        WomenBlouses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","WomenBlouses");
                startActivity(intent);
            }
        });

        WomenTrousers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","WomenTrousers");
                startActivity(intent);
            }
        });

        GentsShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","GentsShirts");
                startActivity(intent);
            }
        });

        GentsTrousers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","GentsTrousers");
                startActivity(intent);
            }
        });

        GentsTshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","GentsTshirts");
                startActivity(intent);
            }
        });

        GirlDresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","GirlDresses");
                startActivity(intent);
            }
        });

        GirlSkirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","GirlSkirts");
                startActivity(intent);
            }
        });

        GirlTshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","GirlTshirts");
                startActivity(intent);
            }
        });

        BoyTrousers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","BoyTrousers");
                startActivity(intent);
            }
        });

        BoyTshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","BoyTshirts");
                startActivity(intent);
            }
        });

        hats_caps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","hats_caps");
                startActivity(intent);
            }
        });

        hairAccessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","hairAccessories");
                startActivity(intent);
            }
        });

        socks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","socks");
                startActivity(intent);
            }
        });
    }
}
