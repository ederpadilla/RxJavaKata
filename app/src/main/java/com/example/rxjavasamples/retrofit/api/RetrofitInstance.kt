package com.example.rxjavasamples.retrofit.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private var retrofit: Retrofit? = null
    private val BASE_URL = "https://jsonplaceholder.typicode.com/"

    private val okHttpClient = OkHttpClient().newBuilder()
        .addNetworkInterceptor( HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        .connectTimeout(120,TimeUnit.SECONDS)
        .readTimeout(120,TimeUnit.SECONDS)
        .writeTimeout(120,TimeUnit.SECONDS)
        .build()



    val service: StarWarsServices?
        get() {

            if (retrofit == null) {

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()


            }

            return retrofit?.create(StarWarsServices::class.java)
        }
}
