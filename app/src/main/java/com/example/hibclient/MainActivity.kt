package com.example.hibclient

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity() : AppCompatActivity() {

    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: ReposAdapter


    companion object {

        const val LOGIN = "login"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var back_btn = findViewById<ImageView>(R.id.back_btn)
        back_btn.setOnClickListener(){
            this.finish()
        }


        var login: String = intent.getStringExtra("login").toString()
        layoutManager = LinearLayoutManager(this)

        getUserRepos(login)
    }

    fun fin(){
        Toast.makeText(applicationContext, "Неправильный логин", Toast.LENGTH_SHORT).show()
        this.finish()
    }

    private fun getUserRepos(user: String){
        val repoService = Common.retrofitService
        repoService.getUsersList(user).enqueue(object : Callback<ArrayList<Repositories>> {

            override fun onFailure(call: Call<ArrayList<Repositories>>, t: Throwable) {
                fin()

            }

            override fun onResponse(
                call: Call<ArrayList<Repositories>>,
                response: Response<ArrayList<Repositories>>
            ) {
                if (response.isSuccessful) {
                    val recyclerView: RecyclerView = findViewById(R.id.repo_recycler)
                    var asset = applicationContext.assets
                        .open("colors.json")
                        .bufferedReader().use{
                            it.readText()
                        }
                    val type = object : TypeToken<Map<String?, String?>?>() {}.type
                    val color = Gson().fromJson<Map<String, String>>(asset, type) //Зачем генерить мап каждый раз когда приходит запрос если это можно сделать один раз? хз
                    recyclerView.layoutManager = layoutManager
                    adapter = ReposAdapter(response.body() as ArrayList<Repositories>, color)
                    adapter.notifyDataSetChanged()
                    recyclerView.adapter = adapter
                } else {
                    fin()
                }
            }
        })
    }
}