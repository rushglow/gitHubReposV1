package com.example.hibclient

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val reposIntent = Intent(this, MainActivity::class.java)

        val button = findViewById<Button>(R.id.btn_sign_in)
        val loginEt = findViewById<EditText>(R.id.tokenEt)



        button.setOnClickListener(){
            val login = loginEt.text.toString()
            reposIntent.putExtra(MainActivity.LOGIN, login)
            startActivity(reposIntent)
        }
    }
}

