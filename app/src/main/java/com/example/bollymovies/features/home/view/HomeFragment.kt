package com.example.bollymovies.features.home.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bollymovies.adapter.HomeAdapter
import com.example.bollymovies.databinding.FragmentHomeBinding
import com.example.bollymovies.datamodels.Movie
import com.example.bollymovies.features.home.viewmodel.HomeViewModel
import com.example.bollymovies.features.moviedetails.view.MovieDetailsActivity
import com.example.bollymovies.utils.onMovieClickListener


class HomeFragment : Fragment(), onMovieClickListener {

    private var binding: FragmentHomeBinding? = null
    private lateinit var viewModel: HomeViewModel

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
            viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        }

        fun setupLancamentosRecyclerView(lista: List<Movie>) {
            val lancamentosAdapter = HomeAdapter(lista)

            binding?.vgCardsListLancamentos?.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = lancamentosAdapter
            }
        }

        fun setupPopularRecyclerView(lista: List<Movie>) {
            val popularAdapter = HomeAdapter(lista)
            binding?.vgCardsListPopular?.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = popularAdapter
            }
        }

        fun setupSugestionRecyclerView(lista: List<Movie>) {
            val sugestionAdapter = HomeAdapter(lista)
            binding?.vgCardsListSugestion?.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = sugestionAdapter
            }
        }

        fun carregarLancamentos() {
            viewModel.buscarLancamentos().observe(viewLifecycleOwner, Observer { movies ->
                setupLancamentosRecyclerView(movies)
            })
        }

        fun carregarPopular() {
            viewModel.buscarPopular().observe(viewLifecycleOwner, Observer { movies ->
                setupPopularRecyclerView(movies)
            })
        }

        fun carregarSugestion() {
            viewModel.buscarSugestion().observe(viewLifecycleOwner, Observer { movies ->
                setupSugestionRecyclerView(movies)
            })
        }

        carregarSugestion()
        carregarPopular()
        carregarLancamentos()

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onClickListener(movie: Movie) {
        val intent = Intent(context, MovieDetailsActivity::class.java)
        startActivity(intent)
    }
}