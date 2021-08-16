package com.example.bollymovies.features.mylist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bollymovies.adapter.MyListAdapter
import com.example.bollymovies.databinding.FragmentMyListBinding
import com.example.bollymovies.datamodels.Movie
import com.example.bollymovies.features.mylist.viewmodel.MyListViewModel
import com.example.bollymovies.utils.GridSpacingItemDecoration


class MyListFragment : Fragment() {

    private var binding: FragmentMyListBinding? = null
    private val viewModel: MyListViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val observer = Observer<List<Movie>>{
                myList -> setupRecyclerView(myList)

        }

        viewModel.buscarFilmes().observe(this, observer)
    }
    fun setupRecyclerView(lista: List<Movie>){
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

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}