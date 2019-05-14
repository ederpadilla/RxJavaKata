package com.example.rxjavasamples.movies.model

/**
 * Created by K. A. ANUSHKA MADUSANKA on 7/4/2018.
 */

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieDBResponse : Parcelable {

    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("total_results")
    @Expose
    var totalMovies: Int? = null
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null
    @SerializedName("results")
    @Expose
    var movies: List<Movie>? = null

    protected constructor(`in`: Parcel) {
        this.page = `in`.readValue(Int::class.java.classLoader) as Int
        this.totalMovies = `in`.readValue(Int::class.java.classLoader) as Int
        this.totalPages = `in`.readValue(Int::class.java.classLoader) as Int
        `in`.readList(this.movies, Movie::class.java.classLoader)
    }

    constructor() {}

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeValue(page)
        dest.writeValue(totalMovies)
        dest.writeValue(totalPages)
        dest.writeList(movies)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Creator<MovieDBResponse> {
        override fun createFromParcel(parcel: Parcel): MovieDBResponse {
            return MovieDBResponse(parcel)
        }

        override fun newArray(size: Int): Array<MovieDBResponse?> {
            return arrayOfNulls(size)
        }
    }

}

