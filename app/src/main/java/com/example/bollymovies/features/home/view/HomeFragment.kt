package com.example.bollymovies.features.home.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bollymovies.adapter.HomeAdapter
import com.example.bollymovies.base.BaseFragment
import com.example.bollymovies.databinding.FragmentHomeBinding
import com.example.bollymovies.datamodels.Movie
import com.example.bollymovies.features.home.viewmodel.HomeViewModel
import com.example.bollymovies.features.moviedetails.view.MovieDetailsActivity
import com.example.bollymovies.model.Result
import com.example.bollymovies.utils.Command
import com.example.bollymovies.utils.ConstantsApp.Home.KEY_INTENT_MOVIE_ID
import com.example.bollymovies.utils.onMovieClickListener
import com.google.android.material.snackbar.Snackbar


class HomeFragment : BaseFragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel


    private val nowPlayingAdapter: HomeAdapter by lazy {
        HomeAdapter { movie ->
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(KEY_INTENT_MOVIE_ID, movie?.id ?: -1)
            startActivity(intent)
        }
    }

    private val popularAdapter: HomeAdapter by lazy {
        HomeAdapter { movie ->
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(KEY_INTENT_MOVIE_ID, movie?.id ?: -1)
            startActivity(intent)
        }
    }

    private val topRatedAdapter: HomeAdapter by lazy {
        HomeAdapter { movie ->
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(KEY_INTENT_MOVIE_ID, movie?.id ?: -1)
            startActivity(intent)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.let {
            viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
            viewModel.command = command

            setupRecyclerViews()
            loadNowPlaying()
            loadPopular()
            loadTopRated()

        }
    }

    private fun setupRecyclerViews() {
        binding?.vgCardsListLancamentos?.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = nowPlayingAdapter
        }
        binding?.vgCardsListPopular?.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularAdapter
        }
        binding?.vgCardsListTopRated?.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = topRatedAdapter
        }
    }

    private fun loadNowPlaying() {
        viewModel.nowPlayingPagedList?.observe(viewLifecycleOwner, { pagedList ->
            nowPlayingAdapter.submitList(pagedList)
        })
    }

    private fun loadPopular() {
        viewModel.popularPagedList?.observe(viewLifecycleOwner, { pagedList ->
            popularAdapter.submitList(pagedList)
        })
    }

    private fun loadTopRated() {
        viewModel.topRatedPagedList?.observe(viewLifecycleOwner, { pagedList ->
            topRatedAdapter.submitList(pagedList)
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override var command: MutableLiveData<Command> = MutableLiveData()
}