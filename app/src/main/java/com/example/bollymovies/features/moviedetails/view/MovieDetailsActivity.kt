package com.example.bollymovies.features.moviedetails.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.*
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
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
import com.google.android.datatransport.runtime.scheduling.SchedulingConfigModule_ConfigFactory.config
import java.io.ByteArrayOutputStream


class MovieDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var viewModel: MovieDetailsViewModel
    private var movieId: Int? = null
//    private var permissions = arrayOf(
//        Manifest.permission.READ_EXTERNAL_STORAGE,
//        Manifest.permission.WRITE_EXTERNAL_STORAGE
//    )
    private var image: Bitmap? = null
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
//           this.image = getBitmapFromView(binding.ivMovieDetailsImage)
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

            binding.tvMyListLabel.setOnClickListener{
                if (binding.cbMyListMovies.isChecked){
                    binding.cbMyListMovies.isChecked = false
                    viewModel.deleteMyListMovieDb(movie)
                } else {
                    binding.cbMyListMovies.isChecked = true
                    viewModel.saveMyListMovieDb(movie)
                }
            }

            binding.tvWatchedMovies.setOnClickListener{
                if (binding.cbWatchedMovies.isChecked){
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
    }

    private fun goToShare() {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "text/plain"
        share.putExtra(Intent.EXTRA_TEXT, "Vi este filme no App BollyMovies e lembrei de você!\n\n$sharedTitle ($year)\n\n$overview")
        startActivity(Intent.createChooser(share, this.getString(R.string.txt_shared_title)))
    }
//    private fun getBitmapFromView(view: ImageView): Bitmap? {
//        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
//        val canvas = Canvas(bitmap)
//        view.draw(canvas)
//        return bitmap
//    }
//
//    private fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
//        val bytes = ByteArrayOutputStream()
//        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
//        val path =
//            MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
//        return Uri.parse(path)
//    }
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == 100) {
//            when {
//                grantResults.isNotEmpty()
//                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
//                -> { goToShare() }
//
//                else -> {
//                    val result1 =
//                        ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
//                    if (result1 != PackageManager.PERMISSION_GRANTED) {
//                        Toast.makeText(this, "Você negou o acesso à sua galeria", Toast.LENGTH_SHORT).show()
//                    } else {
//                        goToShare()
//                    }
//                }
//            }
//        }
//        return
//    }
//
//    private fun checkPermissions(){
//        var result: Int
//        val listPermissionsNeeded: MutableList<String> = ArrayList()
//
//        for (p in permissions) {
//            result = ContextCompat.checkSelfPermission(this, p)
//            if (result != PackageManager.PERMISSION_GRANTED) {
//                listPermissionsNeeded.add(p)
//            } else {
//                goToShare()
//            }
//        }
//        if (listPermissionsNeeded.isNotEmpty()) {
//            ActivityCompat.requestPermissions(
//                this,
//                listPermissionsNeeded.toTypedArray(),
//                100
//            )
//        }
//    }

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
                }
            }
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



