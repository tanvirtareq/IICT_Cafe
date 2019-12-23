package com.example.iictbeta2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.iictbeta2.AccActivity.HomeActivity;
import com.example.iictbeta2.SettingsRechargeActivities.RechargeActivity;
import com.google.firebase.auth.FirebaseAuth;

public class Main2Activity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("IICT Cafe");

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);



        NavigationView drawernavigationview=findViewById(R.id.nav_view);
        drawernavigationview.setNavigationItemSelectedListener(this);


        drawerLayout=findViewById(R.id.drawerLayout);



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new MessageFragment()).commit();
//            navigationView.setCheckedItem(R.id.nav_message);
//        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileFragment()).commit();
                break;

            case R.id.balance:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new BalanceFragment()).commit();
                break;

            case R.id.recharge:
                Intent gorecharge = new Intent(this, RechargeActivity.class);
                startActivity(gorecharge);
                break;

            case R.id.logout:
                sendToStart();
                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment=null;

                    switch (menuItem.getItemId())
                    {
                        case R.id.navigation_homeItem:
                            selectedFragment=new HomeFragment();
                            break;

                        case R.id.navigationMessageItem:
                            selectedFragment=new MessageFragment();
                            break;

                        case R.id.navigationPendingOrdersItem:
                            selectedFragment=new PendingItemFragment();
                            break;

                        case R.id.cart:
                            selectedFragment=new CartFragment();
                            break;


                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
                    return  true;
                }
            };

    void sendToStart(){
        FirebaseAuth.getInstance().signOut();
        Intent gohome = new Intent(this, HomeActivity.class);
        startActivity(gohome);
        finish();
    }
}
