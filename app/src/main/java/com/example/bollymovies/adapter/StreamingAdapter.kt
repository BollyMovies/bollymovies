package com.example.bollymovies.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bollymovies.databinding.ItemStreamingBinding
import com.example.bollymovies.model.Streaming
import com.example.bollymovies.features.moviedetails.view.MovieDetailsActivity

class StreamingAdapter(
    private val streamingList: List<Streaming>
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
            stream: Streaming,
        ) {
            with(binding) {
                ivStreaming.setImageResource(stream.capa)
            }
        }
    }

    fun onClick(v: View?) {
        val intent = Intent(v?.context, MovieDetailsActivity::class.java)
        v?.context?.let { ContextCompat.startActivity(it, intent, null) }
    }

    companion object {
        const val VIEW_TYPE_DEFAULT = 1
    }
}