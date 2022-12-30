package com.canberkzdmr.moviesapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canberkzdmr.moviesapplication.base.SingleLiveEvent
import com.canberkzdmr.moviesapplication.domain.repository.MovieRepository
import com.canberkzdmr.moviesapplication.model.movieoverview.Result
import com.canberkzdmr.moviesapplication.util.Constants
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    private val _upcomingMovies = MutableLiveData<List<Result>>()
    val upcomingMovies: LiveData<List<Result>> = _upcomingMovies

    private val _upcomingSlideModel = MutableLiveData<ArrayList<SlideModel>>()
    val upcomingSlideModel: LiveData<ArrayList<SlideModel>> = _upcomingSlideModel

    private val _movies = MutableLiveData<List<Result>>()
    val movies: LiveData<List<Result>> = _movies

    val movieSingleEvent = SingleLiveEvent(-1)

    init {
        refreshData()
    }

    fun refreshData() {
        viewModelScope.launch {
            setSlideModel()
            _movies.postValue(repository.getUpcomingMovies().results)
        }
    }

    private suspend fun setSlideModel() {
        val upcomingMovies = repository.getNowPlayingMovies().results
        _upcomingMovies.postValue(upcomingMovies)
        val imageList = ArrayList<SlideModel>()
        for (movie in upcomingMovies) {
            imageList.add(
                SlideModel(
                    Constants.IMAGE_URL + movie.backdrop_path,
                    movie.title,
                    ScaleTypes.CENTER_CROP
                )
            )
        }
        _upcomingSlideModel.postValue(imageList)
    }

    fun onClickMovie(movie: Result) {
        movieSingleEvent.postValue(movie.id)
    }

    fun getDetail(slideModel: SlideModel?) {
        slideModel?.let {
            if (getMovieIdByTitle(slideModel.title!!) != -1) {
                movieSingleEvent.postValue(getMovieIdByTitle(slideModel.title!!))
            }
        }
    }

    private fun getMovieIdByTitle(title: String): Int {
        var tmpMovie = Result(
            false, "",
            listOf(), -1, "", "", "", -1.0, ", ", "", "", false, -1.0, -1
        )
        for (movie in _upcomingMovies.value!!) {
            if (movie.title == title) {
                tmpMovie = movie
            }
        }
        return tmpMovie.id
    }
}