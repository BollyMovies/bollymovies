package com.example.bollymovies.features.home.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
    private val takeShimmerTime = 1500L
    private lateinit var handler: Handler



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

            handler = Handler(Looper.getMainLooper())

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

    private fun loadNowPlaying() {
        viewModel.nowPlayingPagedList?.observe(viewLifecycleOwner, { pagedList ->
//            binding?.shimmerNowPlaying?.stopShimmer()
//            binding?.shimmerNowPlaying?.visibility = View.GONE
//            binding?.vgCardsListNowPlaying?.visibility = View.VISIBLE
            nowPlayingAdapter.currentList?.clear()
            nowPlayingAdapter.submitList(pagedList, null)
            nowPlayingAdapter.notifyDataSetChanged()
            handler.postDelayed({ binding?.shimmerNowPlaying?.visibility = View.GONE },takeShimmerTime)


        })
    }

    private fun loadPopular() {
        viewModel.popularPagedList?.observe(viewLifecycleOwner, { pagedList ->
//            binding?.shimmerPopular?.stopShimmer()
//            binding?.shimmerPopular?.visibility = View.GONE
//            binding?.vgCardsListPopular?.visibility = View.VISIBLE
            popularAdapter.currentList?.clear()
            popularAdapter.submitList(pagedList)
            popularAdapter.notifyDataSetChanged()
            handler.postDelayed({ binding?.shimmerPopular?.visibility = View.GONE },takeShimmerTime)



        })
    }

    private fun loadTopRated() {
        viewModel.topRatedPagedList?.observe(viewLifecycleOwner, { pagedList ->
//            binding?.shimmerTopRated?.stopShimmer()
//            binding?.shimmerTopRated?.visibility = View.GONE
//            binding?.vgCardsListTopRated?.visibility = View.VISIBLE
            topRatedAdapter.currentList?.clear()
            topRatedAdapter.submitList(pagedList)
            topRatedAdapter.notifyDataSetChanged()
            handler.postDelayed({ binding?.shimmerTopRated?.visibility = View.GONE },takeShimmerTime)


        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override var command: MutableLiveData<Command> = MutableLiveData()
}