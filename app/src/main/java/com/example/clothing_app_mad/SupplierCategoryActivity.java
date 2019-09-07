package com.example.clothing_app_mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class SupplierCategoryActivity extends AppCompatActivity {

    private ImageView fDresses, fSkirts, fBlouses, fTrousers;
    private ImageView mShirts, mTrousers, mTshirts;
    private ImageView gDresses, gSkirts, gTshirts;
    private ImageView bTrousers, bTshirts;
    private ImageView hats_caps, hairAcc, socks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_category);

        fDresses = findViewById(R.id.female_dresses);
        fSkirts = findViewById(R.id.female_skirts);
        fBlouses = findViewById(R.id.female_blouses);
        fTrousers = findViewById(R.id.female_trousers);

        mShirts = findViewById(R.id.gents_shirts);
        mTrousers = findViewById(R.id.gents_trousers);
        mTshirts = findViewById(R.id.gents_tshirts);

        gDresses = findViewById(R.id.girl_dresses);
        gSkirts = findViewById(R.id.girl_skirts);
        gTshirts = findViewById(R.id.gents_tshirts);

        bTrousers = findViewById(R.id.boy_trousers);
        bTshirts = findViewById(R.id.boy_tshirts);

        hats_caps = findViewById(R.id.hatsCaps);
        hairAcc = findViewById(R.id.hair_accessories);
        socks  = findViewById(R.id.socks);

        fDresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","fDresses");
                startActivity(intent);
            }
        });

        fSkirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","fSkirts");
                startActivity(intent);
            }
        });

        fBlouses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","fBlouses");
                startActivity(intent);
            }
        });

        fTrousers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","fTrousers");
                startActivity(intent);
            }
        });

        mShirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","mShirts");
                startActivity(intent);
            }
        });

        mTrousers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","mTrousers");
                startActivity(intent);
            }
        });

        mTshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","mTshirts");
                startActivity(intent);
            }
        });

        gDresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","gDresses");
                startActivity(intent);
            }
        });

        gSkirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","gSkirts");
                startActivity(intent);
            }
        });

        gTshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","gTshirts");
                startActivity(intent);
            }
        });

        bTrousers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","bTrousers");
                startActivity(intent);
            }
        });

        bTshirts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","bTshirts");
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

        hairAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SupplierCategoryActivity.this, SupplierAddNewProductActivity.class);
                intent.putExtra("category","hairAcc");
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
