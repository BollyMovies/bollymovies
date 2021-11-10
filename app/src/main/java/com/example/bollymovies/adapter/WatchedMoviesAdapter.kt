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
import com.example.bollymovies.database.WatchedMoviesList
import com.example.bollymovies.databinding.MainCardItemBinding
import com.example.bollymovies.features.moviedetails.view.MovieDetailsActivity
import com.example.bollymovies.utils.ConstantsApp

class WatchedMoviesAdapter(
    private val watchedMovies: List<WatchedMoviesList>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MainCardItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ViewHolder
        holder.bind(watchedMovies[position])
    }

    override fun getItemCount() = watchedMovies.size

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE_DEFAULT
    }

    inner class ViewHolder(
        val binding: MainCardItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            movie: WatchedMoviesList,
        ) {
            with(binding) {
                tvMovieTitle.text = movie.title
                Glide
                    .with(itemView.context)
                    .load(movie.posterPath)
                    .placeholder(R.drawable.placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(ivMovieImage)
                binding.vgMainCard.setOnClickListener {
                    onClick(binding.vgMainCard, movie)
                }
            }
        }
    }

    fun onClick(v: View?, movie: WatchedMoviesList) {
        val intent = Intent(v?.context, MovieDetailsActivity::class.java)
        intent.putExtra(ConstantsApp.Home.KEY_INTENT_MOVIE_ID, movie.movieId ?: -1)
        v?.context?.let { ContextCompat.startActivity(it, intent, null) }
    }

    companion object {
        const val VIEW_TYPE_DEFAULT = 1
    }
}