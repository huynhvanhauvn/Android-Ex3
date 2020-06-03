package com.sbro.androidex3.APIService

import com.sbro.androidex3.Data
import com.sbro.androidex3.Movie
import retrofit2.Call
import retrofit2.http.GET

interface MovieAPI {
    @GET("movie/top_rated")
    fun getTopRatedMovie():Call<Data>
    @GET("movie/now_playing")
    fun getNowPlayingMovie(): Call<Data>
}