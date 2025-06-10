package com.example.news_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(NewsModel news);
    }

    private Context context;
    private List<NewsModel> newsList;
    private OnItemClickListener listener;

    public NewsAdapter() {
        this.newsList = new ArrayList<>();
    }

    public NewsAdapter(Context context, List<NewsModel> newsList, OnItemClickListener listener) {
        this.context = context;
        this.newsList = newsList;
        this.listener = listener;
    }

    public void updateList(List<NewsModel> newList) {
        newsList = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.activity_item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsModel news = newsList.get(position);
        holder.title.setText(news.getTitle());
        holder.description.setText(news.getDescription());

        if (context != null) {
            Glide.with(context).load(news.getImageUrl()).into(holder.image);
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(news);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView image;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.news1_title);
            description = itemView.findViewById(R.id.news_description);
            image = itemView.findViewById(R.id.news1_img);
        }
    }
}
