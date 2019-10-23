package com.example.mymoviedb.api

import com.example.mymoviedb.BuildConfig
import com.example.mymoviedb.models.Movie
import com.example.mymoviedb.models.MovieQueryResponse
import com.example.mymoviedb.utils.Logger
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val CACHE_SIZE: Long = 5 * 1024 * 1024

class TMDbClient(cacheDir: File) {

    private val cache: Cache by lazy {
        Cache(cacheDir, CACHE_SIZE)
    }

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(HttpInterceptor())
            .build()
    }

    private val tmdbService: TMDbAPI by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(TMDbAPI::class.java)
    }

    fun movieQuery(query: String): Call<MovieQueryResponse> {
        return tmdbService.gueryMovies(BuildConfig.MOVIE_DB_KEY, query)
    }

    fun getMovie(id: Int): Call<Movie> {
        return tmdbService.getMovie(id, BuildConfig.MOVIE_DB_KEY)
    }

    inner class HttpInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            Logger.log("http request: ${chain.request().url()}")

            return chain.proceed(chain.request())
        }
    }
}