package com.sbro.androidex3.APIService

import com.sbro.androidex3.MainActivity
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieService {
    private lateinit var api:MovieAPI
    init {
        api=createInstance()
    }
    companion object{
        private var mInstance :MovieService ?=null
        fun getInstance() = mInstance?: synchronized(this){
            mInstance ?: MovieService().also { mInstance=it }
        }
    }
    private fun createInstance():MovieAPI{
        val okHttpClient=OkHttpClient.Builder().addInterceptor(AuthInterceptor()).build()
        val retrofit=Retrofit.Builder()
            .baseUrl(MainActivity.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(MovieAPI::class.java)
    }
    fun getApi():MovieAPI =api
}