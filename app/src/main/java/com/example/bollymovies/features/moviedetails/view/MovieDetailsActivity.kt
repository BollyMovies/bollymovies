package com.example.bollymovies.features.moviedetails.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.bollymovies.adapter.StreamingAdapter
import com.example.bollymovies.databinding.ActivityMovieDetailsBinding
import com.example.bollymovies.extensions.getFirst4Chars
import com.example.bollymovies.model.Streaming
import com.example.bollymovies.features.moviedetails.viewmodel.MovieDetailsViewModel
import com.example.bollymovies.utils.Command
import com.example.bollymovies.utils.ConstantsApp.Home.KEY_INTENT_MOVIE_ID


class MovieDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var viewModel: MovieDetailsViewModel
    private var movieId: Int? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        movieId = intent.getIntExtra(KEY_INTENT_MOVIE_ID, -1)
        this@MovieDetailsActivity.let {
            viewModel = ViewModelProvider(this)[MovieDetailsViewModel::class.java]
            viewModel.command = command
            viewModel.getMovieById(movieId)


        }
        fun setupObservables() {
            viewModel.onSuccessMovieById.observe(this, { it ->
                it?.let { movie ->
                    with(binding) {
//                        contentError.isVisible = false
//                        contentLayout.isVisible = true

                        this@MovieDetailsActivity.let { activityNonNull ->
                            Glide
                                .with(activityNonNull)
                                .load(movie.poster_path)
                                .into(ivMovieDetailsImage)

                            tvMovieName.text = movie.title
                            tvDescriptionText.text = movie.overview
                            tvYear.text = movie.release_date.toString().getFirst4Chars()
                            tvTime.text = "${movie.runtime.toString()} min."
                            movie.vote_average?.let {
                                binding.ratingBarFilmsSeries.rating = (it / 2.0f).toFloat()
                                binding.ratingBarFilmsSeries.stepSize = 0.5f
                                binding.tvCast.text =
                                    movie.credits?.getCastName(context = this@MovieDetailsActivity)
                            }


                        }
                    }
                }
            })

            viewModel.command.observe(this, {
                when (it) {
                    is Command.Loading -> {

                    }
                    is Command.Error -> {
//                        binding?.contentLayout?.isVisible = false
//                        binding?.contentError?.isVisible = true
                    }
                }
            })

            binding.ivArrowBack.setOnClickListener {
                finish()
            }
        }

        setupObservables()



        fun setupStreamingRecyclerView(listaStreaming: List<Streaming>) {
            val streamingAdapter = StreamingAdapter(listaStreaming)
            binding.vgStreaming.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = streamingAdapter
            }
        }

    }


    var command: MutableLiveData<Command> = MutableLiveData()

}



