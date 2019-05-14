package com.example.rxjavasamples.movies.view

import android.content.res.Configuration
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.example.rxjavasamples.DebugUtils

import com.example.rxjavasamples.movies.adapter.MovieAdapter
import com.example.rxjavasamples.movies.model.Movie
import com.example.rxjavasamples.movies.model.MovieDBResponse
import com.example.rxjavasamples.movies.service.RetrofitInstance
import com.example.rxjavasamples.R

import java.net.SocketTimeoutException
import java.util.ArrayList

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesActivity : AppCompatActivity() {

    private var movies: ArrayList<Movie> = ArrayList()
    private var recyclerView: RecyclerView? = null
    private var movieAdapter: MovieAdapter? = null
    private var swipeContainer: SwipeRefreshLayout? = null
    private var call: Call<MovieDBResponse>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)


        supportActionBar!!.title = " TMDb Popular Movies Today"
        swipeContainer = findViewById(R.id.swipe_layout)
        swipeContainer!!.setColorSchemeResources(R.color.colorPrimary)
        swipeContainer!!.setOnRefreshListener { getPopularMovies() }
        init()
        getPopularMovies()

    }

    private fun getPopularMovies() {
        DebugUtils.showLog("TAG","Entra aca")
        val getMoviesDataService = RetrofitInstance.service
        call = getMoviesDataService.getPopularMovies(this.getString(R.string.api_key))
        call!!.enqueue(object : Callback<MovieDBResponse> {
            override fun onResponse(call: Call<MovieDBResponse>, response: Response<MovieDBResponse>) {

                val movieDBResponse = response.body()

                if (movieDBResponse != null && movieDBResponse.movies != null) {


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

        DebugUtils.showLog("TAG","Entra aca 2")


    }


    fun init() {


        recyclerView = findViewById(R.id.rvMovies)
        movieAdapter = MovieAdapter(this, movies)


        if (this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            recyclerView!!.layoutManager = GridLayoutManager(this, 2)
        } else {
            recyclerView!!.layoutManager = GridLayoutManager(this, 4)
        }

        recyclerView!!.itemAnimator = DefaultItemAnimator()
        recyclerView!!.adapter = movieAdapter
        movieAdapter!!.notifyDataSetChanged()


    }

    public override fun onDestroy() {
        super.onDestroy()
        if (call != null) {
            if (call!!.isExecuted) {

                call!!.cancel()
            }
        }
    }
}


