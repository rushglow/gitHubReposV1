package com.example.hibclient

object Common {
    private val BASE_URL = "https://api.github.com/users/"
    val retrofitService: RetrofitServices
        get()=RetrofitCient.getClient(BASE_URL).create(RetrofitServices::class.java)
}