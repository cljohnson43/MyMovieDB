package com.example.mymoviedb.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mymoviedb.R
import com.example.mymoviedb.adapters.MovieAdapter
import com.example.mymoviedb.models.Movie
import com.example.mymoviedb.models.MovieBrief
import com.example.mymoviedb.presenters.MovieQuery
import com.example.mymoviedb.presenters.MovieQueryView
import com.example.mymoviedb.viewmodels.MoviePageViewModel
import kotlinx.android.synthetic.main.fragment_search_database.*

class SearchDBFragment : Fragment(), MovieQueryView, MovieAdapter.MovieSelector {

    val model: MoviePageViewModel by lazy {
        requireActivity().run {
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(MoviePageViewModel::class.java)
        }
    }

    private val movieQuery: MovieQuery by lazy {
        requireActivity().run {
            MovieQuery(this@SearchDBFragment, this.cacheDir, model)
        }
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

        displayQueryResults()
    }

    fun submit() {
        val query = et_search_input.text.toString()
        movieQuery.queryMovies(query)
    }

    override fun displayQueryResults() {
        model.queryResults?.let {
            rv_results_list.adapter = MovieAdapter(it, this)
        }
    }

    override fun displayQueryError() {
        Toast.makeText(
            this.context,
            "There was a problem retrieving results for your query. Try a different query",
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun displayGetMovieError() {
        Toast.makeText(
            this.context,
            "There was a problem getting info for your movie selection. Try again at a later time.",
            Toast.LENGTH_SHORT
        ).show()
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