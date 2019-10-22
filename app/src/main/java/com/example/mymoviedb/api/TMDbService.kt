package com.example.mymoviedb.api

import com.example.mymoviedb.BuildConfig
import com.example.mymoviedb.models.Movie
import com.example.mymoviedb.models.MovieQueryResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://api.themoviedb.org/3/"

class TMDbService {

    private val tmdbService: TMDbAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TMDbAPI::class.java)
    }

    fun movieQuery(query: String): Call<MovieQueryResponse> {
        return tmdbService.gueryMovies(BuildConfig.MOVIE_DB_KEY, query)
    }

    fun getMovie(id: Int): Call<Movie> {
        return tmdbService.getMovie(id, BuildConfig.MOVIE_DB_KEY)
    }
}