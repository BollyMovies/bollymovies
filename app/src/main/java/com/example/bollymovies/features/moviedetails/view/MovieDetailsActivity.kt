package com.example.bollymovies.features.moviedetails.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.bollymovies.adapter.StreamingAdapter
import com.example.bollymovies.database.MoviesList
import com.example.bollymovies.database.WatchedMoviesList
import com.example.bollymovies.databinding.ActivityMovieDetailsBinding
import com.example.bollymovies.extensions.getFirst4Chars
import com.example.bollymovies.extensions.toMovie
import com.example.bollymovies.features.moviedetails.viewmodel.MovieDetailsViewModel
import com.example.bollymovies.model.Movie
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
            viewModel.getMyListMoviesDb()
            viewModel.getWatchedMoviesDb()

        }
        fun setupObservables() {

            viewModel.onSuccessMyListFromDb.observe(this, { list ->
                for (moviesList in list) {
                    if (moviesList.movieId == movieId){
                        binding.cbMyListMovies.isChecked = true
                    }
                }
            })

            viewModel.onSuccessWatchedFromDb.observe(this, { watched ->
                for (movie in watched) {
                    if (movie.movieId == movieId){
                        binding.cbWatchedMovies.isChecked = true
                    }
                }
            })

            viewModel.onSuccessMovieById.observe(this, {
                setupData(it)

                val id = it.id
                val poster = it.poster_path
                val title = it.title
                val movie = MoviesList(id, title, poster)
                val watched = WatchedMoviesList(id, title, poster)

                binding.cbMyListMovies.setOnClickListener {
                    if (binding.cbMyListMovies.isChecked) {
                        viewModel.saveMyListMovieDb(movie)
                    } else {
                        viewModel.deleteMyListMovieDb(movie)
                    }
                }

                binding.cbWatchedMovies.setOnClickListener {
                    if (binding.cbWatchedMovies.isChecked) {
                        viewModel.saveWatchedMovieDb(watched)
                    } else {
                        viewModel.deleteWatchedMovieDb(watched)
                    }
                }

                binding.vgStreaming.apply {
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = it.streaming?.results?.BR?.let { it1 ->
                        it1.flatrate?.let { it2 ->
                            StreamingAdapter(it2)
                        }
                    }
                }
        })

        viewModel.onSucessMovieByIdeFromDb.observe(this, {
            setupData(it.toMovie())
        })

        viewModel.command.observe(this, {
            when (it) {
                is Command.Loading -> {

                }
                is Command.Error -> {
                    viewModel.getMovieByIdFromDb(movieId!!)
                }
            }
        })

        binding.ivArrowBack.setOnClickListener {
            finish()
        }
    }
    setupObservables()


}

fun setupData(movie: Movie) {
    with(binding) {
        this@MovieDetailsActivity.let { activityNonNull ->
            Glide
                .with(activityNonNull)
                .load(movie.poster_path)
                .into(ivMovieDetailsImage)

            tvMovieName.text = movie.title
            tvDescriptionText.text = movie.overview
            tvYear.text = movie.release_date.toString().getFirst4Chars()
            tvTime.text = textIsNotNull(movie.runtime.toString())
            movie.vote_average?.let {
                binding.ratingBarFilmsSeries.rating = (it / 2.0f).toFloat()
                binding.tvCast.text =
                    movie.credits?.getCastName(context = this@MovieDetailsActivity)
            }
        }
    }
}

fun textIsNotNull(text: String): String {
    if (text != "null") {
        return "$text min."
    } else {
        return " "
    }
}

var command: MutableLiveData<Command> = MutableLiveData()

}



