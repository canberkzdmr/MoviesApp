package com.canberkzdmr.moviesapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.canberkzdmr.moviesapplication.domain.repository.MovieRepository
import com.canberkzdmr.moviesapplication.model.moviedetail.MovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {
    private val _movie = MutableLiveData<MovieDetail>()
    val movie: LiveData<MovieDetail> = _movie

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch() {
            _movie.postValue(repository.getMovieDetail(movieId))
        }
    }
}