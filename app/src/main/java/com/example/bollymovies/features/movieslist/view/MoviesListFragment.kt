package com.example.bollymovies.features.movieslist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bollymovies.R
import com.example.bollymovies.databinding.FragmentHomeBinding
import com.example.bollymovies.databinding.FragmentMoviesListBinding


class MoviesListFragment : Fragment() {

    private var binding: FragmentMoviesListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}