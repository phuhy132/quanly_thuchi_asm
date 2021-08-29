package com.example.asm_gd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.asm_gd.Fragment.Giaodich_fragment;
import com.example.asm_gd.Fragment.Phanloai_fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState ==null){
            Phanloai_fragment fragment = new  Phanloai_fragment();
                    getSupportFragmentManager().beginTransaction().add(R.id.fame_layout,fragment).commit();
        }
        
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
        int itemId= item.getItemId();
        Fragment fragment = new Fragment();
        switch (itemId){
            case R.id.nav_loai:
                fragment=new Phanloai_fragment();
                break;
            case R.id.nav_Khoanthuchi:
                fragment=new Giaodich_fragment();
                break;

        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fame_layout,fragment).commit();
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}