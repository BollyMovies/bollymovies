package com.example.bollymovies.features.home.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bollymovies.adapter.HomeAdapter
import com.example.bollymovies.base.BaseFragment
import com.example.bollymovies.databinding.FragmentHomeBinding
import com.example.bollymovies.features.home.viewmodel.HomeViewModel
import com.example.bollymovies.features.moviedetails.view.MovieDetailsActivity
import com.example.bollymovies.utils.Command
import com.example.bollymovies.utils.ConstantsApp.Home.KEY_INTENT_MOVIE_ID


class HomeFragment : BaseFragment() {

    private var binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel


    private val nowPlayingAdapter: HomeAdapter by lazy {
        HomeAdapter({ stopShimmer() }, { movie ->
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(KEY_INTENT_MOVIE_ID, movie?.id ?: -1)
            startActivity(intent)
        }
        )
    }

    private val popularAdapter: HomeAdapter by lazy {
        HomeAdapter({ stopShimmer() }, { movie ->
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(KEY_INTENT_MOVIE_ID, movie?.id ?: -1)
            startActivity(intent)
        })
    }

    private val topRatedAdapter: HomeAdapter by lazy {
        HomeAdapter({ stopShimmer() }, { movie ->
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(KEY_INTENT_MOVIE_ID, movie?.id ?: -1)
            startActivity(intent)
        })
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
        binding?.vgCardsListNowPlaying?.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = nowPlayingAdapter
            binding?.shimmerNowPlaying?.startShimmer()
        }
        binding?.vgCardsListPopular?.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularAdapter
            binding?.shimmerPopular?.startShimmer()
        }
        binding?.vgCardsListTopRated?.apply {
            layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = topRatedAdapter
            binding?.shimmerTopRated?.startShimmer()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadNowPlaying() {
        viewModel.nowPlayingPagedList?.observe(viewLifecycleOwner, { pagedList ->

            nowPlayingAdapter.currentList?.clear()
            nowPlayingAdapter.submitList(pagedList, null)
            nowPlayingAdapter.notifyDataSetChanged()
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadPopular() {
        viewModel.popularPagedList?.observe(viewLifecycleOwner, { pagedList ->

            popularAdapter.currentList?.clear()
            popularAdapter.submitList(pagedList)
            popularAdapter.notifyDataSetChanged()

        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadTopRated() {
        viewModel.topRatedPagedList?.observe(viewLifecycleOwner, { pagedList ->

            topRatedAdapter.currentList?.clear()
            topRatedAdapter.submitList(pagedList)
            topRatedAdapter.notifyDataSetChanged()
        })
    }

    private fun stopShimmer() {
        binding?.let {
            with(it) {
                shimmerTopRated.stopShimmer()
                vgCardsListTopRated.visibility = View.VISIBLE
                vgCardsListPopular.visibility = View.VISIBLE
                vgCardsListNowPlaying.visibility = View.VISIBLE
                shimmerTopRated.visibility = View.INVISIBLE
                shimmerPopular.stopShimmer()
                shimmerPopular.visibility = View.INVISIBLE
                shimmerNowPlaying.stopShimmer()
                shimmerNowPlaying.visibility = View.INVISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override var command: MutableLiveData<Command> = MutableLiveData()
}