package com.example.mymoviedb.viewmodels

import androidx.lifecycle.ViewModel
import com.example.mymoviedb.models.Movie

class MoviePageViewModel : ViewModel() {

    var movieSelected: Movie? = null

    fun selectMovie(movie: Movie) { movieSelected = movie }
}