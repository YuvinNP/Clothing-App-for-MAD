package com.example.clothing_app_mad;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.clothing_app_mad.Entites.Seller;
import com.example.clothing_app_mad.Gents.Gents;
import com.example.clothing_app_mad.Kids.Kids;
import com.example.clothing_app_mad.Ladies.Ladies;
import com.example.clothing_app_mad.Ladies.Ladies_Dresses;
import com.example.clothing_app_mad.Prevalent.Prevalent;

import com.example.clothing_app_mad.Seller.SellerMaintainProductsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import com.google.android.material.tabs.TabLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.viewpager.widget.ViewPager;

import android.view.Menu;

import androidx.recyclerview.widget.RecyclerView;

import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;


public class NavDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, All_items.OnFragmentInteractionListener, Ladies.OnFragmentInteractionListener, Gents.OnFragmentInteractionListener, Kids.OnFragmentInteractionListener
{

    private DatabaseReference ProductRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private TextView usernametxt;

    //to preview updated products by seller
    private String type = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_nav_drawer);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null)
        {
            type = getIntent().getExtras().get("Seller").toString();
        }

        TabLayout tabLayout = findViewById (R.id.tablayout);
        tabLayout.addTab (tabLayout.newTab ().setText ("All"));
        tabLayout.addTab (tabLayout.newTab ().setText ("Gents"));
        tabLayout.addTab (tabLayout.newTab ().setText ("Ladies"));
        tabLayout.addTab (tabLayout.newTab ().setText ("Kids"));
        tabLayout.setTabGravity (TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById (R.id.viewpager);

        final PagerAdapter adapter = new PagerAdapter (getSupportFragmentManager (), tabLayout.getTabCount ());
        viewPager.setAdapter (adapter);
        viewPager.setOnPageChangeListener (new TabLayout.TabLayoutOnPageChangeListener (tabLayout));

        tabLayout.setOnTabSelectedListener (new TabLayout.OnTabSelectedListener () {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem (tab.getPosition ());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        ValueEventListener valueEventListener = new ValueEventListener () {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };


//        paper.init.(this);

        Toolbar toolbar = findViewById (R.id.customToolbar);
        toolbar.setTitle ("Home");

        setSupportActionBar (toolbar);


        //to go to the cart activity from the navdrawer floating button
        FloatingActionButton fab = findViewById (R.id.fab);
        fab.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {

                if (Prevalent.currentOnlineSeller == null){

                    Intent intent = new Intent(NavDrawer.this, CartActivity.class);
                    startActivity(intent);

                }

            }
        });

        DrawerLayout drawer = findViewById (R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle (
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener (toggle);
        toggle.syncState ();

        NavigationView navigationView = findViewById (R.id.nav_view);
        navigationView.setNavigationItemSelectedListener (this);

        View headerView = navigationView.getHeaderView (0);
        TextView userNameTextView = headerView.findViewById (R.id.user_name);
        TextView userEmail = headerView.findViewById (R.id.user_email);
        CircleImageView profImageView = headerView.findViewById (R.id.user_image);

        if (Prevalent.currentOnlineSeller == null) {
            userNameTextView.setText (Prevalent.currentOnlineUser.getCname ());
            Picasso.get ().load (Prevalent.currentOnlineUser.getImage ()).into (profImageView);
            userEmail.setText (Prevalent.currentOnlineUser.getEmail ());
        } else {
            userNameTextView.setText (Prevalent.currentOnlineSeller.getSname ());
           // Picasso.get ().load (Prevalent.currentOnlineCustomer.getImage ()).into (profImageView);
            userEmail.setText (Prevalent.currentOnlineSeller.getEmail ());
        }


    }

    @Override
    protected void onStart() {
        super.onStart();

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
      /*  if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        int id = menuItem.getItemId();

        if (id == R.id.nav_cart)
        {
            //go to cart activity from the navdrawer side bar
            if (Prevalent.currentOnlineSeller == null){

                Intent intent = new Intent(NavDrawer.this, CartActivity.class);
                startActivity(intent);

            }


        }
        else if (id == R.id.nav_search)
        {
            Intent intent = new Intent(NavDrawer.this, SearchActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_notification)
        {

        }
        else if (id == R.id.nav_setting)
        {
            if (Prevalent.currentOnlineSeller == null){
                Intent intent = new Intent(NavDrawer.this, UpdateCustomer.class);
                startActivity(intent);

            }


        }
        else if (id == R.id.nav_logout)
        {
//            Paper.book().destroy();
//            FirebaseAuth.getInstance ().signOut ();
//            finish();
            Intent intent = new Intent(NavDrawer.this, Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }


}
