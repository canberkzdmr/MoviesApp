package com.canberkzdmr.moviesapplication.data.repository

import com.canberkzdmr.moviesapplication.data.remote.MovieApi
import com.canberkzdmr.moviesapplication.domain.repository.MovieRepository
import com.canberkzdmr.moviesapplication.model.moviedetail.MovieDetail

/**
 * Created on 12/25/2022.
 */
class MovieRepositoryImpl(
    private val api: MovieApi
): MovieRepository {

    override suspend fun getUpcomingMovies() = api.getUpcomingMovies()

    override suspend fun getNowPlayingMovies() = api.getNowPlayingMovies()

    override suspend fun getMovieDetail(movieId: Int) = api.getMovieDetail(movieId)
}