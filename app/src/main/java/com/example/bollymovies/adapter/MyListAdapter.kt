package com.example.bollymovies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bollymovies.databinding.MyListCardItemBinding
import com.example.bollymovies.datamodels.Movie

class MyListAdapter(
    private val myList: List<Movie>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MyListCardItemBinding
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
        val binding: MyListCardItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            movie: Movie,
        ) {
            with(binding) {
                tvMoviesListTitle.text = movie.titulo
                ivMovieImage.setImageResource(movie.capa)
            }
        }
    }

    companion object {
        const val VIEW_TYPE_DEFAULT = 1
    }
}