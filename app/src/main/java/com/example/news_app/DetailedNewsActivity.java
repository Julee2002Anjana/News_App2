package com.example.news_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailedNewsActivity extends AppCompatActivity {

    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_news);

        // Initialize back button
        backButton = findViewById(R.id.back_button);

        // Set click listener to go back to NewsActivity
        backButton.setOnClickListener(v -> openNewsActivity());
    }

    private void openNewsActivity() {
        Intent intent = new Intent(DetailedNewsActivity.this, NewsActivity.class);
        startActivity(intent);
        finish(); // Close DetailedNewsActivity to prevent stacking
    }
}
