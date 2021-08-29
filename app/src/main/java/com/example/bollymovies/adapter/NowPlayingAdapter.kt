//package com.example.bollymovies.adapter
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.bollymovies.R
//import com.example.bollymovies.databinding.WatchCardItemBinding
//import com.example.bollymovies.model.Result
//
//class NowPlayingAdapter(
//    private val nowPlayingList: List<Result>,
//    private val onClickListener: (movie: Result) -> Unit
//) : RecyclerView.Adapter<NowPlayingAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val binding = WatchCardItemBinding
//            .inflate(LayoutInflater.from(parent.context), parent, false)
//        return ViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.bind(nowPlayingList[position], onClickListener)
//    }
//
//    override fun getItemCount() = nowPlayingList.size
//
//    class ViewHolder(
//        private val binding: WatchCardItemBinding
//    ) : RecyclerView.ViewHolder(binding.root) {
//
//        fun bind(
//            movie: Result,
//            onClickListener: (movie: Result) -> Unit,
//        ) {
//            with(binding) {
//                tvWatchTitle.text = movie.title
//                cvWatch.setOnClickListener {
//                    onClickListener(movie)
//                }
//                Glide
//                    .with(itemView.context)
//                    .load(movie.poster_path)
//                    .placeholder(R.drawable.no_image_available)
//                    .into(ivWatchImage)
//            }
//        }
//    }
//}