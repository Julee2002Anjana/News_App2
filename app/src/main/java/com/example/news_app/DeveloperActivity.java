package com.example.news_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DeveloperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        Button exitButton = findViewById(R.id.exit_button);

        exitButton.setOnClickListener(v -> {
            Intent intent = new Intent(DeveloperActivity.this, NewsActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
