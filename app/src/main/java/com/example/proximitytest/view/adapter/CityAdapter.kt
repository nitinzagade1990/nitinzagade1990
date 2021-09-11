package com.example.proximitytest.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proximitytest.R
import com.example.proximitytest.database.City

class NewsAdapter(var newsList: List<City>) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.NewsViewHolder {

        return NewsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_city, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsAdapter.NewsViewHolder, position: Int) {
        holder.newsTitle?.text = newsList.get(position).city
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var newsTitle: TextView? = null

        init {
            newsTitle = itemView.findViewById(R.id.city_name);
        }
    }

}