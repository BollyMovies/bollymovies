package com.example.bollymovies.features.mylist.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bollymovies.adapter.HomeAdapter
import com.example.bollymovies.adapter.MyListAdapter
import com.example.bollymovies.database.MoviesList
import com.example.bollymovies.databinding.FragmentMyListBinding

import com.example.bollymovies.features.moviedetails.view.MovieDetailsActivity
import com.example.bollymovies.features.mylist.viewmodel.MyListViewModel
import com.example.bollymovies.utils.Command
import com.example.bollymovies.utils.ConstantsApp
import com.example.bollymovies.utils.GridSpacingItemDecoration


class MyListFragment : Fragment() {

    private var binding: FragmentMyListBinding? = null
    private lateinit var viewModel: MyListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this)[MyListViewModel::class.java]
        viewModel.command = command
        viewModel.getMyListMoviesDb()



    }

    fun setupObservable() {
        viewModel.onSucessMyListFromDb.observe(viewLifecycleOwner, {
            setupRecyclerView(it)
        })
    }

    fun setupRecyclerView(lista: List<MoviesList>) {
        val myListAdapter = MyListAdapter(lista)
        val spanCount = 2
        val spacing = 50
        val includeEdge = true

        binding?.let {
            with(it) {
                vgMyList.adapter = myListAdapter
                vgMyList.layoutManager = GridLayoutManager(context, spanCount)
                vgMyList.addItemDecoration(
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
        binding = FragmentMyListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservable()
            }


    var command: MutableLiveData<Command> = MutableLiveData()

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}