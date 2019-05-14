package com.example.rxjavasamples.movies.service

import com.example.rxjavasamples.movies.model.MovieDBResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesDataService {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<MovieDBResponse>

    @GET("movie/popular")
    fun getPopularMoviesWithRx(@Query("api_key")
                               apiKey: String): Observable<MovieDBResponse>

    @GET("movie/popular")
    fun getPopularMoviesWithRxFlowable(@Query("api_key")
                                       apiKey: String): Flowable<MovieDBResponse>
}
