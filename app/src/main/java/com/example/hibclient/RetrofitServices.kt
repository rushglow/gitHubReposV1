package com.example.hibclient

import retrofit2.Call
import retrofit2.http.*

interface RetrofitServices {
    @GET("{user}/repos") //Добавляется к baseUrl из  файла Common
    fun getUsersList(@Path("user") user: String): Call<ArrayList<Repositories>>
}