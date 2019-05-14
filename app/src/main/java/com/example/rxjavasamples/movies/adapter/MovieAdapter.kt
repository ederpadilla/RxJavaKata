package com.example.rxjavasamples.movies.adapter

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

import com.example.rxjavasamples.movies.model.Movie
import com.example.rxjavasamples.movies.view.MovieActivity
import com.bumptech.glide.Glide
import com.example.rxjavasamples.R

import java.util.ArrayList

/**
 * Created by K. A. ANUSHKA MADUSANKA on 7/4/2018.
 */
class MovieAdapter(private val context: Context, private val movies: ArrayList<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_list_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        holder.movieTitle.text = movies[position].originalTitle
        holder.rate.text = java.lang.Double.toString(movies[position].voteAverage!!)

        val posterPath = "https://image.tmdb.org/t/p/w500" + movies[position].posterPath!!

        Glide.with(context)
                .load(posterPath)
                .centerCrop()
                .placeholder(R.drawable.loading)
                .into(holder.movieImage)

    }

    override fun getItemCount(): Int {

        return movies.size
    }


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var movieTitle: TextView
        var rate: TextView
        var movieImage: ImageView


        init {

            movieTitle = itemView.findViewById(R.id.tvTitle)
            rate = itemView.findViewById(R.id.tvRating)
            movieImage = itemView.findViewById(R.id.ivMovie)

            itemView.setOnClickListener { view ->
                val position = adapterPosition

                if (position != RecyclerView.NO_POSITION) {
                    val selectedMovie = movies[position]
                    val intent = Intent(context, MovieActivity::class.java)
                    intent.putExtra("movie", selectedMovie)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    val movieTitle = selectedMovie.originalTitle
                    Toast.makeText(view.context, movieTitle, Toast.LENGTH_LONG).show()

                    context.startActivity(intent)
                }
            }
        }
    }


}
