package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
DrawerLayout drawerLayout;
NavigationView navigationView;
Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent ihome=new Intent(MainActivity.this, Loginpage.class);
                startActivity(ihome);
            }
        },20000);




        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this, drawerLayout , toolbar , R.string.OpenDrawer , R.string.CloseDrawer);

        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if(id==R.id.Home){
                    loadFragment(new FragmentA());
                    Toast.makeText(MainActivity.this , "Home", Toast.LENGTH_SHORT).show();
                } else if (id==R.id.Features) {
                        loadFragment(new FragmentB());
                        Toast.makeText(MainActivity.this , "Features", Toast.LENGTH_SHORT).show();
                } else if (id==R.id.Contact) {
                    loadFragment(new FragmentC());
                    Toast.makeText(MainActivity.this , "Contact", Toast.LENGTH_SHORT).show();
                } else{
                    loadFragment(new FragmentD());
                    Toast.makeText(MainActivity.this , "Help", Toast.LENGTH_SHORT).show();
                }

                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });


    }

    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void loadFragment(Fragment fragment) {

        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft= fm.beginTransaction();

        ft.add(R.id.container , fragment);
        ft.commit();


    }
}