package com.example.flickrsearchapp.network

import com.example.flickrsearchapp.Model.PhotosResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import com.google.gson.GsonBuilder


/**
 * This is a retrofit service call for making api call
 */
interface RetrofitService {
    @GET("rest")
    fun getPhotos(
        @Query("method") method: String,
        @Query("api_key") api_key: String,
        @Query("format") format: String,
        @Query("tags") tags: String,
        @Query("extras") extras: String,
        @Query("nojsoncallback") nojsoncallback: Int
    ): Call<PhotosResponse>


    companion object {
        var gson = GsonBuilder()
            .setLenient()
            .create()
        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("http://api.flickr.com/services/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }

}