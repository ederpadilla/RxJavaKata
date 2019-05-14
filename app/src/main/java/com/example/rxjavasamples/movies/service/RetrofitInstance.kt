package com.example.rxjavasamples.movies.service

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by K. A. ANUSHKA MADUSANKA on 7/4/2018.
 */
object RetrofitInstance {

    private var retrofit: Retrofit? = null
    private val BASE_URL = "http://api.themoviedb.org/3/"

    private val okHttpClient = OkHttpClient().newBuilder()
        .addNetworkInterceptor( HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(120,TimeUnit.SECONDS)
        .readTimeout(120,TimeUnit.SECONDS)
        .writeTimeout(120,TimeUnit.SECONDS)
        .build()

    val service: MoviesDataService
        get() {


            if (retrofit == null) {

                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(okHttpClient)
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }



            return retrofit!!.create(MoviesDataService::class.java)
        }


}
