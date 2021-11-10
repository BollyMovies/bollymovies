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
    private val list = mutableListOf<MoviesList>()
    private val adapter = MyListAdapter(list)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[MyListViewModel::class.java]
        viewModel.command = command
        // Inflate the layout for this fragment
        binding = FragmentMyListBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservable()
    }


    private fun setupObservable() {
        viewModel.onSuccessMyListFromDb.observe(viewLifecycleOwner, {
            list.clear()
            list.addAll(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getMyListMoviesDb()
    }

    private fun setupRecyclerView() {
        val spanCount = 2
        val spacing = 50
        val includeEdge = true

        binding?.let {
            with(it) {
                vgMyList.setHasFixedSize(true)
                vgMyList.adapter = adapter
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