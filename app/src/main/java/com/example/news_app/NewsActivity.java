package com.example.news_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import android.widget.SearchView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;


public class NewsActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private NewsAdapter adapter;
    private List<NewsModel> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        db = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.news_recycler_view);
        searchView = findViewById(R.id.search_view);
        ImageView acadBtn = findViewById(R.id.academic_icon);
        ImageView sportBtn = findViewById(R.id.sport_icon);
        ImageView eventBtn = findViewById(R.id.event_icon);

        // New: Developer & User Profile icons
        ImageView developerBtn = findViewById(R.id.developer_icon);
        ImageView userProfileBtn = findViewById(R.id.user_profile_icon);

        newsList = new ArrayList<>();
        adapter = new NewsAdapter(this, newsList, news -> {
            Intent intent = new Intent(NewsActivity.this, DetailActivity.class);
            intent.putExtra("title", news.getTitle());
            intent.putExtra("description", news.getDescription());
            intent.putExtra("imageUrl", news.getImageUrl());
            intent.putExtra("date", news.getDate());
            intent.putExtra("category", news.getCategory());
            startActivity(intent);
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Developer icon click → open DeveloperActivity
        developerIcon.setOnClickListener(v -> openDeveloperActivity());


        // Load default category
        loadNewsByCategory("Academic");


        acadBtn.setOnClickListener(v -> loadNewsByCategory("Academic"));
        sportBtn.setOnClickListener(v -> loadNewsByCategory("Sport"));
        eventBtn.setOnClickListener(v -> loadNewsByCategory("Event"));

        // New: Open MainActivity from developer icon
        developerBtn.setOnClickListener(v -> {
            Intent intent = new Intent(NewsActivity.this, MainActivity.class);
            startActivity(intent);
        });

        // New: Open MainActivity from user profile icon
        userProfileBtn.setOnClickListener(v -> {
            Intent intent = new Intent(NewsActivity.this, MainActivity.class);
            startActivity(intent);
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterNews(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterNews(newText);
                return true;
            }
        });
    }

    private void loadNewsByCategory(String category) {
        Log.d("FirestoreDebug", "Fetching news for category: " + category);

        db.collection("news")
                .whereEqualTo("category", category)
                .get()
                .addOnSuccessListener(query -> {
                    newsList.clear();
                    for (DocumentSnapshot doc : query.getDocuments()) {
                        NewsModel news = doc.toObject(NewsModel.class);
                        if (news != null) {
                            newsList.add(news);
                            Log.d("FirestoreDebug", "Fetched: " + news.getTitle());
                        } else {
                            Log.e("FirestoreError", "Null news object");
                        }
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> Log.e("FirestoreError", "Error fetching news", e));

        // Rectangle click → open DetailedNewsActivity
        rectangle.setOnClickListener(v -> openDetailedNews("general"));
    }

    private void openDetailedNews(String category) {
        Intent intent = new Intent(NewsActivity.this, DetailedNewsActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);

    }

    private void filterNews(String query) {
        List<NewsModel> filtered = new ArrayList<>();
        for (NewsModel news : newsList) {
            if (news.getTitle().toLowerCase().contains(query.toLowerCase())) {
                filtered.add(news);
            }
        }
        adapter.updateList(filtered);
    }

    private void openDeveloperActivity() {
        Intent intent = new Intent(NewsActivity.this, DeveloperActivity.class);
        startActivity(intent);
    }
}
