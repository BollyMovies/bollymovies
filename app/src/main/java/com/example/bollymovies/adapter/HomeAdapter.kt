package com.example.bollymovies.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.bollymovies.R
import com.example.bollymovies.databinding.MainCardItemBinding
import com.example.bollymovies.datamodels.Movie
import com.example.bollymovies.features.home.view.HomeFragment
import com.example.bollymovies.features.moviedetails.view.MovieDetailsActivity
import com.example.bollymovies.features.usersettings.view.UserSettingsActivity
import com.example.bollymovies.utils.onMovieClickListener

class HomeAdapter(
    private val movies: List<Movie>,
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), View.OnClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MainCardItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ViewHolder
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size

    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE_DEFAULT
    }

    inner class ViewHolder(
        val binding: MainCardItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            movie: Movie,
        ) {
            with(binding) {
                tvMovieTitle.text = movie.titulo
                ivMovieImage.setImageResource(movie.capa)
                binding.vgMainCard.setOnClickListener{
                    onClick(binding.vgMainCard)
                }

            }

        }
    }

    companion object {
        const val VIEW_TYPE_DEFAULT = 1
    }

    override fun onClick(v: View?) {
        val intent = Intent(v?.context, MovieDetailsActivity::class.java)
        v?.context?.let { startActivity(it, intent, null) }
    }
}