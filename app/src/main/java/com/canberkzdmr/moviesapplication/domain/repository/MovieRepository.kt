package com.canberkzdmr.moviesapplication.domain.repository

import com.canberkzdmr.moviesapplication.model.moviedetail.MovieDetail
import com.canberkzdmr.moviesapplication.model.movieoverview.Movie

/**
 * Created on 12/25/2022.
 */
interface MovieRepository {

    suspend fun getUpcomingMovies(): Movie

    suspend fun getNowPlayingMovies(): Movie

    suspend fun getMovieDetail(movieId: Int): MovieDetail
}