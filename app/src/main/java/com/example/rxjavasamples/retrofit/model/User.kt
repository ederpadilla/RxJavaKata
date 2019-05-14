package com.example.rxjavasamples.retrofit.model

data class User(
    val userMail : String,
    val password : String,
    var id : Int?= null
)