package com.example.mymoviedb.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mymoviedb.R
import com.example.mymoviedb.models.MovieBrief
import com.example.mymoviedb.presenters.MovieQuery
import com.example.mymoviedb.presenters.MovieQueryView
import com.example.mymoviedb.utils.Logger
import kotlinx.android.synthetic.main.fragment_search_database.*

class SearchDBFragment : Fragment(), MovieQueryView {

    private val movieQuery = MovieQuery(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_database, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btn_search_db.setOnClickListener { view -> submit(view) }
    }

    fun submit(view: View) {
        val query = et_search_input.text.toString()
        movieQuery.queryMovies(query)
    }

    override fun displayQueryResults(movies: List<MovieBrief>) {
        movies.forEach {
            Logger.log("Result: ${it.title}")
        }
    }

    override fun displayQueryError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}