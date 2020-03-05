package com.anish.syrus2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.anish.syrus2020.Frag1;
import com.anish.syrus2020.Frag2;
import com.anish.syrus2020.Frag3;
import com.anish.syrus2020.Frag4;
import com.anish.syrus2020.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setSelectedItemId(R.id.nav_filter);
        bottomNav.bringToFront();
        bottomNav.setOnNavigationItemSelectedListener(navListener);


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contanier, new Frag5()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new Frag1();
                            break;
                        case R.id.nav_favorites:
                            selectedFragment = new NewsFragment();
                            break;
                        case R.id.nav_search:
                            selectedFragment = new Frag3();
                            break;

                        case R.id.nav_person:
                            selectedFragment = new Frag4();
                            break;

                        case R.id.nav_filter:
                            selectedFragment = new Frag5();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contanier, selectedFragment).commit();

                    return true;
                }
            };
}
