package com.example.hibclient

import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitCient {
    private var retrofit: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit{
        if (retrofit == null){
            retrofit = Retrofit.Builder() //экземпляр, который использует интерфейс и API Builder, чтобы задать определение конечной точки URL для операций HTTP
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}