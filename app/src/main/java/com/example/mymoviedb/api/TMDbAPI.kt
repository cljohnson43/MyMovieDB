package com.example.mymoviedb.api

import com.example.mymoviedb.models.MovieQueryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TMDbAPI {
    @GET("/search/movie")
    fun gueryMovies(@Query("api_key") apiKey: String, @Query("query") query: String): Call<MovieQueryResponse>
}