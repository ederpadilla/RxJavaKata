package com.example.rxjavasamples.movies.model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.rxjavasamples.R
import com.example.rxjavasamples.movies.service.RetrofitInstance
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Predicate
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList

class MoviesRepository(private val application: Application) {

    private val compositeDisposable = CompositeDisposable()
    private val moviesLiveData = MutableLiveData<List<Movie>>()
    private var movies: ArrayList<Movie>? = null
    private var movieDBResponseObservable: Observable<MovieDBResponse>? = null

    fun getMoviesLiveData(): MutableLiveData<List<Movie>> {

        movies = ArrayList()
        val getMoviesDataService = RetrofitInstance.service
        movieDBResponseObservable =
            getMoviesDataService.getPopularMoviesWithRx(
                application.applicationContext.getString(R.string.api_key))

        compositeDisposable.add(movieDBResponseObservable!!
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { movieDBResponse -> Observable
                .fromArray(*movieDBResponse.movies!!.toTypedArray()) }
            .filter (Predicate<Movie> { movie ->
                /*movie.voteAverage.let {
                    it > 7.0
                }*/
                movie.voteAverage!! > 7.0
            } )
            .subscribeWith(object : DisposableObserver<Movie>() {
                override fun onNext(movie: Movie) {
                    movies!!.add(movie)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                }

                override fun onComplete() {
                    moviesLiveData.postValue(movies)
                }
            })
        )
        return moviesLiveData
    }

    fun clear() {
        compositeDisposable.clear()
    }

}