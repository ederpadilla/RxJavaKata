package com.example.rxjavasamples.movies.view

import android.os.Bundle
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast

import com.example.rxjavasamples.movies.model.Movie
import com.bumptech.glide.Glide
import com.example.rxjavasamples.R

class MovieActivity : AppCompatActivity() {

    private lateinit var movieTitle: TextView
    private lateinit var movieSynopsis: TextView
    private lateinit var movieRating: TextView
    private lateinit var movieReleaseDate: TextView
    private lateinit var movieImage: ImageView
    private lateinit var ratingBar: RatingBar
    private lateinit var rateButton: Button

    private lateinit var movie: Movie
    var image: String? = null
    var name: String? = null
    var synopsis: String? = null
    lateinit var rating: String
    var date: String? = null
    var myRating: String? = null
    private var id: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        initCollapsingToolbar()

        movieImage = findViewById(R.id.ivMovieLarge)
        movieTitle = findViewById(R.id.tvMovieTitle)
        movieSynopsis = findViewById(R.id.tvPlotsynopsis)
        movieRating = findViewById(R.id.tvMovieRating)
        movieReleaseDate = findViewById(R.id.tvReleaseDate)
        ratingBar = findViewById(R.id.ratingBar)
        rateButton = findViewById(R.id.btnRating)


        val intentThatStartedThisActivity = intent
        if (intentThatStartedThisActivity.hasExtra("movie")) {

            movie = intent.getParcelableExtra("movie")

            image = movie.posterPath
            name = movie.originalTitle
            synopsis = movie.overview
            rating = java.lang.Double.toString(movie.voteAverage!!)
            date = movie.releaseDate
            id = movie.id!!

            val poster = "https://image.tmdb.org/t/p/w500" + image!!

            Glide.with(this)
                    .load(poster)
                    .placeholder(R.drawable.loading)
                    .into(movieImage)

            movieTitle.text = name
            movieSynopsis.text = synopsis
            movieRating.text = rating
            movieReleaseDate.text = date

        } else {
            Toast.makeText(this, "No movie data found", Toast.LENGTH_SHORT).show()
        }


    }


    private fun initCollapsingToolbar() {

        val collapsingToolbarLayout = findViewById<View>(R.id.ctbMovie) as CollapsingToolbarLayout
        collapsingToolbarLayout.title = " "
        val appBarLayout = findViewById<View>(R.id.movieAppbar) as AppBarLayout
        appBarLayout.setExpanded(true)

        appBarLayout.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            internal var isShow = false
            internal var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    // collapsingToolbarLayout.setTitle(getString(R.string.movie_info));
                    collapsingToolbarLayout.title = name
                    isShow = true
                } else if (isShow) {
                    collapsingToolbarLayout.title = " "
                    isShow = false
                }
            }
        })
    }


}
