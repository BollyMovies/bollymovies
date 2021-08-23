package com.example.bollymovies.features.moviedetails.view


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bollymovies.adapter.StreamingAdapter
import com.example.bollymovies.databinding.ActivityMovieDetailsBinding
import com.example.bollymovies.datamodels.Streaming
import com.example.bollymovies.features.moviedetails.viewmodel.MovieDetailsViewModel


class MovieDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this@MovieDetailsActivity.let {
            viewModel = ViewModelProvider(this).get(MovieDetailsViewModel::class.java)
        }


        fun setupStreamingRecyclerView(listaStreaming: List<Streaming>) {
            val streamingAdapter = StreamingAdapter(listaStreaming)
            binding.vgStreaming.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = streamingAdapter
            }
        }

        fun loadStreamings() {
            viewModel.buscarStreamings().observe(this, { streamings ->
                setupStreamingRecyclerView(streamings)
            })

        }
        loadStreamings()
        binding.ivArrowBack.setOnClickListener {
            finish()
        }
    }


    companion object {
        const val EXTRA_DETAILS = "extra_details"

    }
}



