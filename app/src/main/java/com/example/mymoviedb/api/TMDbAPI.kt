package com.example.mymoviedb.api

import com.example.mymoviedb.models.Movie
import com.example.mymoviedb.models.MovieQueryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDbAPI {
    @GET("search/movie")
    fun gueryMovies(@Query("api_key") apiKey: String, @Query("query") query: String): Call<MovieQueryResponse>

    @GET("movie/{movie_id}")
    fun getMovie(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String): Call<Movie>
}