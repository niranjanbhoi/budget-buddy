package com.kimy.budgetbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.cs_toolbar);
        toolbar.setTitle("Budget Buddy");
        setSupportActionBar(toolbar);


        DrawerLayout drawerLayout=findViewById(R.id.drawer);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(

                this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close

        );




        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);









    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawerLayout=findViewById(R.id.drawer);

        if (drawerLayout.isDrawerOpen(GravityCompat.END)){
            drawerLayout.closeDrawer(GravityCompat.END);
        }else {
            super.onBackPressed();
        }
    }

    public void displaySelectedListener(int itemId){

        Fragment fragment=null;

        switch (itemId){

            case R.id.dashboard:
                break;

            case R.id.income:
                break;

            case R.id.expense:
                break;


        }


        if (fragment!=null){
            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main_frame,fragment);
            fragmentTransaction.commit();
        }

        DrawerLayout drawerLayout=findViewById(R.id.drawer);
        drawerLayout.closeDrawer(GravityCompat.START);

    }

    public void setSupportActionBar(Toolbar toolbar) {
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        
        displaySelectedListener(item.getItemId());
        return true;
    }
}