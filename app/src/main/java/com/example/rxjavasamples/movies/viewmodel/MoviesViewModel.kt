package com.example.rxjavasamples.movies.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.rxjavasamples.movies.model.MoviesRepository
import android.text.method.TextKeyListener.clear
import androidx.lifecycle.LiveData
import com.example.rxjavasamples.movies.model.Movie


class MoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val movieRepository: MoviesRepository = MoviesRepository(application)

    val allMovies: LiveData<List<Movie>>
        get() = movieRepository.getMoviesLiveData()

    fun clear() {
        movieRepository.clear()
    }



}
