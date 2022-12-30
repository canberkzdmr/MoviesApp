package com.canberkzdmr.moviesapplication.data.remote

import com.canberkzdmr.moviesapplication.model.moviedetail.MovieDetail
import com.canberkzdmr.moviesapplication.model.movieoverview.Movie
import com.canberkzdmr.moviesapplication.util.Constants.API_KEY
import com.canberkzdmr.moviesapplication.util.Constants.MOVIE
import com.canberkzdmr.moviesapplication.util.Constants.NOW_PLAYING
import com.canberkzdmr.moviesapplication.util.Constants.UPCOMING
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created on 12/25/2022.
 */
interface MovieApi {

    @GET(UPCOMING + API_KEY)
    suspend fun getUpcomingMovies(): Movie

    @GET(NOW_PLAYING + API_KEY)
    suspend fun getNowPlayingMovies(): Movie

    @GET("$MOVIE{id}$API_KEY")
    suspend fun getMovieDetail(@Path("id") movieId: Int): MovieDetail
}