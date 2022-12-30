package com.canberkzdmr.moviesapplication.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.canberkzdmr.moviesapplication.R
import com.canberkzdmr.moviesapplication.adapter.MovieListRecyclerAdapter
import com.canberkzdmr.moviesapplication.databinding.FragmentMainBinding
import com.canberkzdmr.moviesapplication.viewmodel.MainViewModel
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import javax.inject.Inject


class MainFragment @Inject constructor() : Fragment(R.layout.fragment_main) {

    lateinit var viewModel: MainViewModel
    private lateinit var movieAdapter: MovieListRecyclerAdapter
    private lateinit var binding: FragmentMainBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        binding = FragmentMainBinding.bind(view)

        movieAdapter = MovieListRecyclerAdapter(arrayListOf(), viewModel)
        binding.rvMovies.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = movieAdapter
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshData()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        viewModel.refreshData()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.upcomingSlideModel.observe(viewLifecycleOwner) {
            binding.imageSlider.setImageList(it)
            binding.imageSlider.setItemClickListener(object : ItemClickListener {
                override fun onItemSelected(position: Int) {
                    viewModel.getDetail(viewModel.upcomingSlideModel.value?.get(position))
                }
            })
        }
        viewModel.movies.observe(viewLifecycleOwner) {
            movieAdapter.updateMovies(it)
            binding.swipeRefreshLayout.isRefreshing = false
        }
        viewModel.movieSingleEvent.observe(viewLifecycleOwner) {
            val action = MainFragmentDirections.actionMainFragmentToMovieDetailFragment(it!!)
            findNavController().navigate(action)
        }
    }

}