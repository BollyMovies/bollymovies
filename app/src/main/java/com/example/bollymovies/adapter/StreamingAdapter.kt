package com.example.bollymovies.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.bollymovies.R
import com.example.bollymovies.databinding.ItemStreamingBinding
import com.example.bollymovies.model.Streaming
import com.example.bollymovies.features.moviedetails.view.MovieDetailsActivity
import com.example.bollymovies.model.Flatrate

class StreamingAdapter(
    private val streamingList: List<Flatrate>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStreamingBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ViewHolder
        holder.bind(streamingList[position])
    }

    override fun getItemCount() = streamingList.size

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE_DEFAULT
    }

    inner class ViewHolder(
        val binding: ItemStreamingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            watchProvider: Flatrate?
        ) {
            Glide.with(itemView.context)
                .load(watchProvider?.logo_path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.ic_icons8_aplicativo_de_desktop_netflix)
                .into(binding.ivStreaming)
        }
    }

    companion object {
        const val VIEW_TYPE_DEFAULT = 1
    }
}