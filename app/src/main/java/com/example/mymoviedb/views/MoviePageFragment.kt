package com.example.mymoviedb.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mymoviedb.R
import com.example.mymoviedb.databinding.FragmentMoviePageBinding
import com.example.mymoviedb.models.Movie
import com.example.mymoviedb.viewmodels.MoviePageViewModel

class MoviePageFragment : Fragment() {
    val model: MoviePageViewModel by lazy {
        activity?.run {
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(MoviePageViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMoviePageBinding>(
            inflater,
            R.layout.fragment_movie_page,
            container,
            false
        ).apply {
            movie = model.movieSelected ?: Movie()
        }

        return binding.root
    }
}