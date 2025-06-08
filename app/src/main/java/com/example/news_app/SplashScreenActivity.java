package com.example.news_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Delayed navigation to Login screen
        new Handler().postDelayed(() -> {

            Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);

            Intent intent = new Intent(SplashScreenActivity.this, activity_login.class);

            startActivity(intent);
            finish();
        }, 3000); // 3 seconds delay
    }
}

