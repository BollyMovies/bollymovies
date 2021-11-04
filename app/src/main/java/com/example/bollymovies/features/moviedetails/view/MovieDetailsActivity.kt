package com.example.bollymovies.features.moviedetails.view

import android.content.Intent
import android.content.pm.PackageManager.*
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.bollymovies.R
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
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo


class MovieDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var viewModel: MovieDetailsViewModel
    private var movieId: Int? = null
    lateinit var movieFromId: Movie
    private var sharedTitle: String? = null
    private var overview: String? = null
    private var year: String? = null


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
        setupObservables()
        binding.ivArrowBack.setOnClickListener {
            finish()
        }

        binding.btnShare.setOnClickListener {
            sharedTitle = binding.tvMovieName.text.toString()
            overview = binding.tvDescriptionText.text.toString()
            year = binding.tvYear.text.toString()
            goToShare()
        }

        binding.tvShareLabel.setOnClickListener {
            sharedTitle = binding.tvMovieName.text.toString()
            overview = binding.tvDescriptionText.text.toString()
            year = binding.tvYear.text.toString()
            goToShare()
        }
    }

    private fun setupObservables() {

        viewModel.onSuccessMyListFromDb.observe(this, { list ->
            for (moviesList in list) {
                if (moviesList.movieId == movieId) {
                    binding.cbMyListMovies.isChecked = true
                }
            }
        })

        viewModel.onSuccessWatchedFromDb.observe(this, { watched ->
            for (movie in watched) {
                if (movie.movieId == movieId) {
                    binding.cbWatchedMovies.isChecked = true
                }
            }
        })

        viewModel.onSuccessMovieById.observe(this, {
            setupData(it)
            movieFromId = it

            val id = it.id
            val poster = it.poster_path
            val title = it.title
            val movieFromList = MoviesList(id, title, poster)
            val watched = WatchedMoviesList(id, title, poster)

            if (it.videos!!.results.isNotEmpty()){
                binding.btTrailerFilmsSeries.isVisible = true
                }

            binding.cbMyListMovies.setOnClickListener {
                if (binding.cbMyListMovies.isChecked) {
                    viewModel.saveMyListMovieDb(movieFromList)
                } else {
                    viewModel.deleteMyListMovieDb(movieFromList)
                }
            }

            binding.tvMyListLabel.setOnClickListener {
                if (binding.cbMyListMovies.isChecked) {
                    binding.cbMyListMovies.isChecked = false
                    viewModel.deleteMyListMovieDb(movieFromList)
                } else {
                    binding.cbMyListMovies.isChecked = true
                    viewModel.saveMyListMovieDb(movieFromList)
                }
            }

            binding.tvWatchedMovies.setOnClickListener {
                if (binding.cbWatchedMovies.isChecked) {
                    binding.cbWatchedMovies.isChecked = false
                    viewModel.deleteWatchedMovieDb(watched)
                } else {
                    binding.cbWatchedMovies.isChecked = true
                    viewModel.saveWatchedMovieDb(watched)
                }
            }


            binding.cbWatchedMovies.setOnClickListener {
                if (binding.cbWatchedMovies.isChecked) {
                    viewModel.saveWatchedMovieDb(watched)
                } else {
                    viewModel.deleteWatchedMovieDb(watched)
                }
            }

            binding.btTrailerFilmsSeries.setOnClickListener {
                setupVideo(movieFromId)
            }

            binding.vgStreaming.apply {
                layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = it.watch_providers?.results?.BR?.let { it1 ->
                    it1.flatrate?.let { it2 ->
                        StreamingAdapter(it2)
                    }
                }
                if (adapter?.itemCount != 0 && adapter?.itemCount != null) {
                    binding.tvStreaming.visibility = View.VISIBLE
                } else {
                    binding.tvStreaming.visibility = View.GONE
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
    }

    private fun goToShare() {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "text/plain"
        share.putExtra(
            Intent.EXTRA_TEXT,
            "Vi este filme no App BollyMovies e lembrei de você!\n\n$sharedTitle ($year)\n\n$overview"
        )
        startActivity(Intent.createChooser(share, this.getString(R.string.txt_shared_title)))
    }

    private fun setupData(movie: Movie) {
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
                    binding.tvDirector.text =
                        movie.credits?.getDirectorName(this@MovieDetailsActivity)
                }
            }
        }
    }

    private fun setupVideo(movie: Movie) {
            val youtubePlayerView = binding.youtubePlayerDetail
            lifecycle.addObserver(youtubePlayerView)
            val youtube = movie.videos!!.results.last()
            binding.apply {
                btTrailerFilmsSeries.isVisible = false
                clYoutube.isVisible = true
                btnClose.setOnClickListener{
                    clYoutube.isVisible = false
                    btTrailerFilmsSeries.isVisible = true
                }
                youtubePlayerDetail.initialize(object :
                    AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youtube.key.let { key -> youTubePlayer.loadOrCueVideo(lifecycle, key, 0f) }
                    }
                })
                youtubePlayerDetail.isFullScreen()
            }
    }


    private fun textIsNotNull(text: String): String {
        if (text != "null") {
            return "$text min."
        } else {
            return " "
        }
    }

    var command: MutableLiveData<Command> = MutableLiveData()

}



