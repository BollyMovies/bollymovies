package com.example.bollymovies.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bollymovies.databinding.MainCardItemBinding
import com.example.bollymovies.model.Result
import com.bumptech.glide.Glide
import com.example.bollymovies.R
import org.jetbrains.annotations.Contract


class HomeAdapter(
    private val stopShimmer: (movie: Result?) -> Unit,
    private val onClickListener: (movie: Result?) -> Unit
) : PagedListAdapter<Result, HomeAdapter.ViewHolder>(Result.DIFF_CALLBACK) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = MainCardItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onClickListener, stopShimmer)

    }


    override fun getItemViewType(position: Int): Int {
        return VIEW_TYPE_DEFAULT
    }

    inner class ViewHolder(
        val binding: MainCardItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            movie: Result?,
            onClickListener: (movie: Result) -> Unit,
            stopShimmer: (movie: Result?) -> Unit
        ) {
            with(binding) {
                movie?.let {
                    tvMovieTitle.text = movie.title
                    binding.vgMainCard.setOnClickListener {
                        onClickListener(movie)
                    }
                    Glide
                        .with(itemView.context)
                        .load(movie.poster_path)
                        .placeholder(R.drawable.placeholder)
                        .into(ivMovieImage)
                }
            }
                stopShimmer(movie)
        }
    }

    companion object {
        const val VIEW_TYPE_DEFAULT = 1
    }


}