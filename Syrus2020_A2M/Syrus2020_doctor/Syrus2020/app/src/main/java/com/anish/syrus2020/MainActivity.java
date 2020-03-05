package com.anish.syrus2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.anish.syrus2020.Frag1;
import com.anish.syrus2020.Frag2;
import com.anish.syrus2020.Frag3;
import com.anish.syrus2020.Frag4;
import com.anish.syrus2020.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();




        nv = (NavigationView)findViewById(R.id.navView);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.nav_home:
                        selectedFragment = new Frag1();
                        break;
                    case R.id.nav_favorites:
                        selectedFragment = new Frag2();
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
        });



        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_contanier, new Frag1()).commit();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

  /*   Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new Frag1();
                            break;
                        case R.id.nav_favorites:
                            selectedFragment = new Frag2();
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
*/
}
