package com.example.mymoviedb.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mymoviedb.R
import com.example.mymoviedb.adapters.MovieAdapter
import com.example.mymoviedb.models.Movie
import com.example.mymoviedb.models.MovieBrief
import com.example.mymoviedb.presenters.MovieQuery
import com.example.mymoviedb.presenters.MovieQueryView
import com.example.mymoviedb.viewmodels.MoviePageViewModel
import kotlinx.android.synthetic.main.fragment_search_database.*
import java.lang.Exception

class SearchDBFragment : Fragment(), MovieQueryView, MovieAdapter.MovieSelector {

    val model: MoviePageViewModel by lazy {
        activity?.run {
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(MoviePageViewModel::class.java)
        } ?: throw Exception("Illegal Activity")
    }

    private val movieQuery: MovieQuery by lazy {
        activity?.run {
            MovieQuery(this@SearchDBFragment, this.cacheDir)
        } ?: throw Exception("Illegal Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_database, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_search_db.setOnClickListener { submit() }
    }

    fun submit() {
        val query = et_search_input.text.toString()
        movieQuery.queryMovies(query)
    }

    override fun displayQueryResults(movies: List<MovieBrief>) {
        rv_results_list.adapter = MovieAdapter(movies, this)
        rv_results_list.layoutManager = LinearLayoutManager(this.context)
    }

    override fun displayQueryError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayGetMovieError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun movieSelected(movie: MovieBrief) {
        movieQuery.getMovie(movie.id)
    }

    override fun displayMovie(movie: Movie) {
        model.selectMovie(movie)

        val directions = SearchDBFragmentDirections.actionSearchDBFragmentToMoviePageFragment()
        findNavController().navigate(directions)
    }
}