package com.example.moviewer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.moviewer.Fragments.FavouriteFragment;
import com.example.moviewer.Fragments.HomeFragment;
import com.example.moviewer.Fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    // Kelompok 7
    // Nissa Adila Hakim - 2440113055
    // Priskilla Adriani Seciawanto - 2440110356
    // Maevy Marvella - 2440094402
    
    
    
    private BottomNavigationView bottomNav;
    int user_id_logged;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        bottomNav.setOnItemSelectedListener(this);
    }

    private void switchFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout, fragment);
        ft.commit();
    }

    public void init() {
        SharedPreferences sPref = getSharedPreferences("LOG_IN", MODE_PRIVATE);
        user_id_logged = sPref.getInt("id", -1);
        android.util.Log.wtf("id", String.valueOf(user_id_logged));
        switchFragment(new HomeFragment(user_id_logged));
        bottomNav = findViewById(R.id.bottomNav);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.nav_home) {
            switchFragment(new HomeFragment(user_id_logged));
        } else if(item.getItemId() == R.id.nav_favourite) {
            switchFragment(new FavouriteFragment(user_id_logged));
        } else if(item.getItemId() == R.id.nav_profile){
            android.util.Log.wtf("id", String.valueOf(user_id_logged));
            switchFragment(new ProfileFragment(user_id_logged));
        }
        return true;
    }
}
