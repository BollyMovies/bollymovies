package com.example.bollymovies.features.watchedmovies.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bollymovies.adapter.WatchedMoviesAdapter
import com.example.bollymovies.database.WatchedMoviesList
import com.example.bollymovies.databinding.FragmentWatchedMoviesBinding
import com.example.bollymovies.datamodels.Movie
import com.example.bollymovies.features.watchedmovies.viewmodel.WatchedMoviesViewModel
import com.example.bollymovies.utils.Command
import com.example.bollymovies.utils.GridSpacingItemDecoration

class WatchedMoviesFragment : Fragment() {

    private var binding: FragmentWatchedMoviesBinding? = null
    private lateinit var viewModel: WatchedMoviesViewModel
    var command: MutableLiveData<Command> = MutableLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[WatchedMoviesViewModel::class.java]
        viewModel.command = command
        viewModel.getWatchedMoviesDb()

    }

    fun setupObservable() {
        viewModel.onSuccessWatchedMoviesFromDb.observe(viewLifecycleOwner, {
            setupRecyclerView(it)
        })
    }

    private fun setupRecyclerView(lista: List<WatchedMoviesList>) {
        val watchedMoviesAdapter = WatchedMoviesAdapter(lista)
        val spanCount = 2
        val spacing = 50
        val includeEdge = true

        binding?.let {
            with(it) {
                vgWatchedMovies.adapter = watchedMoviesAdapter
                vgWatchedMovies.layoutManager = GridLayoutManager(context, spanCount)
                vgWatchedMovies.addItemDecoration(
                    GridSpacingItemDecoration(
                        spanCount,
                        spacing,
                        includeEdge,
                        0,
                        true
                    )
                )
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWatchedMoviesBinding.inflate(inflater, container, false)
        return binding?.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        setupObservable()
//    }

    override fun onResume() {
        super.onResume()
        setupObservable()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}