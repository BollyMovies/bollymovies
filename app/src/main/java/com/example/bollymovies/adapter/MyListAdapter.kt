package com.example.bollymovies.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bollymovies.database.MoviesList
import com.example.bollymovies.databinding.MainCardItemBinding
import com.example.bollymovies.datamodels.Movie
import com.example.bollymovies.features.moviedetails.view.MovieDetailsActivity
import com.example.bollymovies.utils.ConstantsApp

class MyListAdapter(
    private val myList: List<MoviesList>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MainCardItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ViewHolder
        holder.bind(myList[position])
    }

    override fun getItemCount() = myList.size

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE_DEFAULT
    }

    inner class ViewHolder(
        val binding: MainCardItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            movie: MoviesList,
        ) {
            with(binding) {
                tvMovieTitle.text = movie.title
                Glide
                    .with(itemView.context)
                    .load(movie.posterPath)
                    .into(ivMovieImage)
                binding.vgMainCard.setOnClickListener {
                    onClick(binding.vgMainCard, movie)
                }
            }
        }
    }

    fun onClick(v: View?, movie: MoviesList) {
        val intent = Intent(v?.context, MovieDetailsActivity::class.java)
        intent.putExtra(ConstantsApp.Home.KEY_INTENT_MOVIE_ID, movie.movieId ?: -1)
        v?.context?.let {startActivity(it, intent, null)}
    }

    companion object {
        const val VIEW_TYPE_DEFAULT = 1
    }
}