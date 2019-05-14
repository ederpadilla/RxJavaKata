package com.example.rxjavasamples.retrofit.api

import com.example.rxjavasamples.retrofit.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface StarWarsServices {

    @POST("posts")
    fun postSample(@Body user : User) : Call<User>


}