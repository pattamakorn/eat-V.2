package com.example.eat_master;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new Home()).commit();
                    return true;
                case R.id.favorite:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new Favorite()).commit();
                    return true;
                case R.id.kind_of_food:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new Kind_of_food()).commit();
                    return true;
                case R.id.recentapp:
                    getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new Recent()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new Home()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutop, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem itemtop) {
        int id = itemtop.getItemId();
        switch (id) {
            case R.id.setting_menu:
                //Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new Setting()).commit();
                return true;
            case R.id.logout_menu:
                SharedPreferences sharedpreferences = getSharedPreferences(login.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.clear();
                editor.commit();
                startActivity(new Intent(MainActivity.this,login.class));

            case R.id.profile_menu:
                //Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new Profile()).commit();
            default:
                return super.onOptionsItemSelected(itemtop);
        }
    }

}
