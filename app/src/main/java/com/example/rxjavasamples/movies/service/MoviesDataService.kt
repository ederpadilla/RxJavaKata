package com.example.rxjavasamples.movies.service

import com.example.rxjavasamples.movies.model.MovieDBResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesDataService {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<MovieDBResponse>


}
