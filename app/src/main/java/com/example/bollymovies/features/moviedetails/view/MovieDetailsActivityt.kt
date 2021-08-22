package com.example.bollymovies.features.moviedetails.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bollymovies.databinding.ActivityMovieDeatilsBinding


class MovieDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityMovieDeatilsBinding

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDeatilsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
