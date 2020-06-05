package com.sbro.androidex3.APIService


import com.sbro.androidex3.Data
import com.sbro.androidex3.Movie
import retrofit2.Call
import retrofit2.http.Query
import retrofit2.http.GET

interface MovieAPI {
    @GET("movie/top_rated")
    fun getTopRatedMovie(@Query("page") page: Int):Call<Data>
    @GET("movie/now_playing")
    fun getNowPlayingMovie(@Query("page") page: Int): Call<Data>
}