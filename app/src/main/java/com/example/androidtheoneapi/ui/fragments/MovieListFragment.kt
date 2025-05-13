package com.example.androidtheoneapi.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtheoneapi.R
import com.example.androidtheoneapi.databinding.FragmentMovieListBinding
import com.example.androidtheoneapi.util.Resource
import com.example.androidtheoneapi.viewmodel.TheOneApiViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A fragment representing a list of Items.
 */
@AndroidEntryPoint
class MovieListFragment : Fragment() {

    private lateinit var binding: FragmentMovieListBinding
    private lateinit var movieAdapter: MovieListRecyclerViewAdapter
    private val viewModel: TheOneApiViewModel by viewModels()

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getMovies()

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        movieAdapter = MovieListRecyclerViewAdapter()

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = movieAdapter
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieListBinding.bind(view)

        viewModel.movies.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success<*> -> {
                    response.data?.let { responseData ->
                        movieAdapter.differ.submitList(responseData.movies.toList())
                    }
                }

                is Resource.Error<*> -> {
                    response.message?.let { message ->
                        Toast.makeText(activity, "Sorry, error $message", Toast.LENGTH_LONG).show()
                    }
                }

                is Resource.Loading<*> -> return@Observer
            }
        })
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            MovieListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}