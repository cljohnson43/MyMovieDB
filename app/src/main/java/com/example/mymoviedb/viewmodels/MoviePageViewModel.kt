package com.example.mymoviedb.viewmodels

import androidx.lifecycle.ViewModel
import com.example.mymoviedb.models.Movie
import com.example.mymoviedb.models.MovieBrief

class MoviePageViewModel : ViewModel() {

    var movieSelected: Movie? = null

    var queryResults: List<MovieBrief>? = null

    fun selectMovie(movie: Movie) { movieSelected = movie }

    fun storeQueryResults(results: List<MovieBrief>) {
        queryResults = results
    }
}