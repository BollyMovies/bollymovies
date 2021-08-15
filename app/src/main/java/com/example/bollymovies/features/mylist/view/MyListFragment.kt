package com.example.bollymovies.features.mylist.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bollymovies.R
import com.example.bollymovies.adapter.MyListAdapter
import com.example.bollymovies.databinding.FragmentMyListBinding
import com.example.bollymovies.datamodels.Movie
import com.example.bollymovies.features.mylist.viewmodel.MyListViewModel
import com.example.bollymovies.utils.GridSpacingItemDecoration


class MyListFragment : Fragment() {

    private var binding: FragmentMyListBinding? = null


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

        val movie1 = Movie (
            titulo = "White Tiger",
            capa = R.drawable.white_tiger,
            sinopse = "filme indiano",
        )

        val movie2 = Movie (
            titulo = "Lion",
            capa = R.drawable.lion,
            sinopse = "filme indiano",
        )

        val movie3 = Movie (
            titulo = "Dangal",
            capa = R.drawable.dangal,
            sinopse = "filme indiano",
        )

        val movie4= Movie (
            titulo = "Baaghi",
            capa = R.drawable.baaghi,
            sinopse = "filme indiano",
        )

        val movie5 = Movie (
            titulo = "Bahubali",
            capa = R.drawable.bahubali,
            sinopse = "filme indiano",
        )

        val movie6 = Movie (
            titulo = "Pad Man",
            capa = R.drawable.pad_man,
            sinopse = "filme indiano",
        )

        val myList = listOf(movie1, movie2, movie3, movie4, movie5, movie6)

        val myListAdapter = MyListAdapter(myList)
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

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}