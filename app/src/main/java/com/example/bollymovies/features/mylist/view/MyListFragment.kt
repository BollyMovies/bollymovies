package com.example.bollymovies.features.mylist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bollymovies.adapter.MyListAdapter
import com.example.bollymovies.database.MoviesList
import com.example.bollymovies.databinding.FragmentMyListBinding
import com.example.bollymovies.features.mylist.viewmodel.MyListViewModel
import com.example.bollymovies.utils.Command
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.onSuccessMyListFromDb.observe(viewLifecycleOwner, {
            setupRecyclerView(it)})
        // Inflate the layout for this fragment
        binding = FragmentMyListBinding.inflate(inflater, container, false)
        return binding?.root

    }

    private fun setupRecyclerView(list: MutableList<MoviesList>) {
        val myListAdapter = MyListAdapter(list)
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


    var command: MutableLiveData<Command> = MutableLiveData()

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}