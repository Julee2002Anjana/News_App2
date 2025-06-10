package com.example.news_app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class NewsActivity extends AppCompatActivity {

    ImageView userProfileIcon, developerIcon, rectangle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // Initialize views
        userProfileIcon = findViewById(R.id.user_profile_icon);
        developerIcon = findViewById(R.id.developer_icon);
        rectangle = findViewById(R.id.rectangle);

        // Developer icon click → open MainActivity
        developerIcon.setOnClickListener(v -> openMainActivity());

        // User profile icon click → open MainActivity
        userProfileIcon.setOnClickListener(v -> openMainActivity());

        // Rectangle click → open DetailedNewsActivity
        rectangle.setOnClickListener(v -> openDetailedNews("general")); // Pass category if needed
    }

    private void openDetailedNews(String category) {
        Intent intent = new Intent(NewsActivity.this, DetailedNewsActivity.class);
        intent.putExtra("category", category); // Pass category to next activity
        startActivity(intent);
    }

    private void openMainActivity() {
        Intent intent = new Intent(NewsActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
