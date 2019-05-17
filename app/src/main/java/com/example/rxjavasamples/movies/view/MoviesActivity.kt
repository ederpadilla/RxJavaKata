package com.example.rxjavasamples.movies.view

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.rxjavasamples.util.DebugUtils

import com.example.rxjavasamples.movies.adapter.MovieAdapter
import com.example.rxjavasamples.movies.model.Movie
import com.example.rxjavasamples.movies.model.MovieDBResponse
import com.example.rxjavasamples.movies.service.RetrofitInstance
import com.example.rxjavasamples.R
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import io.reactivex.functions.Predicate
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

import java.net.SocketTimeoutException
import java.util.ArrayList

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesActivity : AppCompatActivity() {

    private var movies: ArrayList<Movie> = ArrayList()
    private var recyclerView: androidx.recyclerview.widget.RecyclerView? = null
    private var movieAdapter: MovieAdapter? = null
    private var swipeContainer: androidx.swiperefreshlayout.widget.SwipeRefreshLayout? = null
    private var call: Call<MovieDBResponse>? = null
    private lateinit var  observable : Observable<MovieDBResponse>
    private val compositeDisposable = CompositeDisposable()
    private lateinit var  flowable : Flowable<MovieDBResponse>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)


        supportActionBar!!.title = " TMDb Popular Movies Today"
        swipeContainer = findViewById(R.id.swipe_layout)
        swipeContainer!!.setColorSchemeResources(R.color.colorPrimary)
        swipeContainer!!.setOnRefreshListener { getRxPopularMovies() }
        //getPopularMovies()
        getRxPopularMovies()
        //getFlowablePopularMovies()

    }

    private fun getFlowablePopularMovies() {
        val getMoviesDataService = RetrofitInstance.service
        flowable = getMoviesDataService
            .getPopularMoviesWithRxFlowable(this.getString(R.string.api_key))
        compositeDisposable.add(
            flowable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({response -> successPopularMoviesFlowable(response)},
                    {error -> onErrorPopularMoviesFlowable(error)})
        )
    }

    private fun onErrorPopularMoviesFlowable(error: Throwable) {
        error.printStackTrace()
    }

    private fun successPopularMoviesFlowable(response: MovieDBResponse) {
        DebugUtils.showLog("TAG","Response ${response.toString()}")
    }


    private fun getRxPopularMovies() {
        val getMoviesDataService = RetrofitInstance.service
        observable = getMoviesDataService
            .getPopularMoviesWithRx(this.getString(R.string.api_key))
        compositeDisposable.add(
            observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(Function<MovieDBResponse, Observable<Movie>> {
                        movieDBResponse ->
                    Observable.fromArray(*movieDBResponse.movies!!.toTypedArray())
                })
                .filter(
                    Predicate<Movie> { movie ->
                        /*movie.voteAverage.let {
                            it > 7.0
                        }*/
                        movie.voteAverage!! > 7.0
                    })
                .subscribeWith(object : DisposableObserver<Movie>(){
                    override fun onComplete() {
                        init()
                        swipeContainer?.isRefreshing = false
                    }

                    override fun onNext(movie: Movie) {
                        movies.add(movie)
                    }

                    override fun onError(e: Throwable) {
                        swipeContainer?.isRefreshing = false
                        e.printStackTrace()
                    }

                })
        )
    }

    private fun successPopularMovies(response: MovieDBResponse) {

        movies = response.movies as ArrayList<Movie>
        init()
        swipeContainer?.isRefreshing = false
    }

    private fun onErrorPopularMovies(error : Throwable){
        error.printStackTrace()
        swipeContainer?.isRefreshing = false
    }

    private fun getPopularMovies() {
        val getMoviesDataService = RetrofitInstance.service
        call = getMoviesDataService.getPopularMovies(this.getString(R.string.api_key))
        call!!.enqueue(object : Callback<MovieDBResponse> {
            override fun onResponse(call: Call<MovieDBResponse>, response: Response<MovieDBResponse>) {

                val movieDBResponse = response.body()

                if (movieDBResponse?.movies != null) {


                    movies = movieDBResponse.movies as ArrayList<Movie>
                    init()


                }

                swipeContainer?.isRefreshing = false
            }

            override fun onFailure(call: Call<MovieDBResponse>, t: Throwable) {
                t.printStackTrace()
                if (t is SocketTimeoutException) {
                    Toast.makeText(applicationContext, "Socket Time out.", Toast.LENGTH_LONG).show()
                }

                swipeContainer?.isRefreshing = false

            }
        })


    }


    fun init() {


        recyclerView = findViewById(R.id.rvMovies)
        movieAdapter = MovieAdapter(this, movies)


        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView!!.layoutManager = androidx.recyclerview.widget.GridLayoutManager(this, 2)
        } else {
            recyclerView!!.layoutManager = androidx.recyclerview.widget.GridLayoutManager(this, 4)
        }

        recyclerView!!.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView!!.adapter = movieAdapter
        movieAdapter!!.notifyDataSetChanged()


    }

    public override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
        if (call != null) {
            if (call!!.isExecuted) {

                call!!.cancel()
            }
        }

    }
}


