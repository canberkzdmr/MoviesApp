package com.canberkzdmr.moviesapplication.view

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.canberkzdmr.moviesapplication.R
import com.canberkzdmr.moviesapplication.databinding.FragmentMovieDetailBinding
import com.canberkzdmr.moviesapplication.model.moviedetail.MovieDetail
import com.canberkzdmr.moviesapplication.model.movieoverview.Result
import com.canberkzdmr.moviesapplication.viewmodel.MovieDetailViewModel
import javax.inject.Inject

class MovieDetailFragment @Inject constructor() : Fragment(R.layout.fragment_movie_detail) {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var binding: FragmentMovieDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MovieDetailViewModel::class.java]
        binding = FragmentMovieDetailBinding.bind(view)
        binding.movie = MovieDetail()

        viewModel.getMovieDetail(requireArguments().get("movieId") as Int)
        viewModel.movie.observe(viewLifecycleOwner) {
            binding.movie = it
        }

        binding.ivIMDb.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.imdb.com/title/${viewModel.movie.value!!.imdb_id}")
            requireActivity().startActivity(intent)
        }
    }
}