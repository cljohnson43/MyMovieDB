package com.example.mymoviedb.presenters

import com.example.mymoviedb.api.TMDbService
import com.example.mymoviedb.models.MovieBrief
import com.example.mymoviedb.models.MovieQueryResponse
import com.example.mymoviedb.utils.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

interface MovieQueryView {
    fun displayQueryResults(movies: List<MovieBrief>)
    fun displayQueryError()
}

interface MovieQueryRepo {
    fun queryMovies(query: String)
}

class MovieQuery(private val view: MovieQueryView) : MovieQueryRepo {
    private val tmdbService = TMDbService()

    override fun queryMovies(query: String) {
        tmdbService.movieQuery(query).enqueue(object : Callback<MovieQueryResponse> {
            override fun onFailure(call: Call<MovieQueryResponse>, t: Throwable) {
                Logger.e("error fetching movie query: ${t.message}")
                view.displayQueryError()
            }

            override fun onResponse(
                call: Call<MovieQueryResponse>,
                response: Response<MovieQueryResponse>
            ) {
                val results = response.body()?.movieBriefs?.map { movie -> movie!! }
                    ?: listOf<MovieBrief>()
                view.displayQueryResults(results)
            }
        })

    }
}