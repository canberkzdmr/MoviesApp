package com.canberkzdmr.moviesapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.canberkzdmr.moviesapplication.R
import com.canberkzdmr.moviesapplication.databinding.MovieRowBinding
import com.canberkzdmr.moviesapplication.model.movieoverview.Result
import com.canberkzdmr.moviesapplication.viewmodel.MainViewModel
import javax.inject.Inject

/**
 * Created on 12/25/2022.
 */
class MovieListRecyclerAdapter @Inject constructor(private val movies: ArrayList<Result>, val viewModel: MainViewModel) :
    RecyclerView.Adapter<MovieListRecyclerAdapter.MovieViewHolder>() {
    class MovieViewHolder(var view: MovieRowBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view =
            DataBindingUtil.inflate<MovieRowBinding>(inflater, R.layout.movie_row, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.view.movie = movies[position]
        holder.view.viewModel = viewModel
    }

    override fun getItemCount(): Int {
        return movies.count()
    }

    fun updateMovies(newMovies: List<Result>) {
        movies.clear().also {
            movies.addAll(newMovies)
            notifyDataSetChanged()
        }
    }
}