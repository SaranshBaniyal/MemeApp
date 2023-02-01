package com.example.memeshare2

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {

    @GET("/gimme")
    fun getMeme() : Call<Meme>
}