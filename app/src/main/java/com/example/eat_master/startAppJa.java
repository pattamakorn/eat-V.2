package com.example.eat_master;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class startAppJa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_start_app_ja);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getBaseContext(),login.class);
                startActivity(i);
                finish();
            }
        };new Handler().postDelayed(runnable,3500);
    }
}
