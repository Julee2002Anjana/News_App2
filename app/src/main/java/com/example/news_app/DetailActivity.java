package com.example.news_app;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView title = findViewById(R.id.news1_title);
        TextView desc = findViewById(R.id.news_description);
        TextView cat = findViewById(R.id.news_category);
        TextView date = findViewById(R.id.news1_date);
        ImageView img = findViewById(R.id.news1_img);

        Bundle data = getIntent().getExtras();
        if (data != null) {
            title.setText(data.getString("title", "No Title"));
            desc.setText(data.getString("description", "No Description"));
            cat.setText(data.getString("category", "No Category"));
            date.setText("Published: " + data.getString("date", "Unknown Date"));
            Glide.with(this).load(data.getString("imageUrl", "")).into(img);
        } else {
            title.setText("No Data Available");
        }

        findViewById(R.id.back_button).setOnClickListener(v -> finish());
    }
}
