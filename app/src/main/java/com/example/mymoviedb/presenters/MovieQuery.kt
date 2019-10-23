package com.example.mymoviedb.presenters

import com.example.mymoviedb.api.TMDbClient
import com.example.mymoviedb.models.Movie
import com.example.mymoviedb.models.MovieBrief
import com.example.mymoviedb.models.MovieQueryResponse
import com.example.mymoviedb.utils.Logger
import com.example.mymoviedb.viewmodels.MoviePageViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

interface MovieQueryView {
    fun displayQueryResults()
    fun displayQueryError()
    fun displayGetMovieError()
    fun displayMovie(movie: Movie)
}

interface MovieQueryRepo {
    fun queryMovies(query: String)
    fun getMovie(id: Int)
}

class MovieQuery(
    private val view: MovieQueryView,
    cacheDir: File,
    private val viewModel: MoviePageViewModel
) : MovieQueryRepo {

    private val tmdbClient = TMDbClient(cacheDir)

    override fun queryMovies(query: String) {
        tmdbClient.movieQuery(query).enqueue(object : Callback<MovieQueryResponse> {
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

                viewModel.storeQueryResults(results)

                view.displayQueryResults()
            }
        })

    }

    override fun getMovie(id: Int) {
        tmdbClient.getMovie(id).enqueue(object : Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Logger.e("error fetching movie $id: ${t.message}")
                view.displayGetMovieError()
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                val movie: Movie? = response.body()
                if (movie != null) view.displayMovie(movie)
                else {
                    Logger.e("response body was null")
                    view.displayGetMovieError()
                }
            }
        })
    }
}