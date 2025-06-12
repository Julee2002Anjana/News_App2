package com.example.news_app;

public class NewsModel {
    private String title, description, imageUrl, date, category;
    private int score; // New field for scoring

    public NewsModel() {}

    public NewsModel(String title, String description, String imageUrl, String date, String category, int score) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.date = date;
        this.category = category;
        this.score = score;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public String getDate() { return date; }
    public String getCategory() { return category; }
    public int getScore() { return score; }

    public void setScore(int score) { this.score = score; }
}
