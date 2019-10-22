package com.example.mymoviedb.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymoviedb.R
import com.example.mymoviedb.models.MovieBrief
import com.google.android.material.card.MaterialCardView

class MovieAdapter(val results: List<MovieBrief>, val delegator: MovieSelector) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        results.get(position).let {
            holder.apply {
                tvReleaseDate.text = it.releaseDate
                tvTitle.text = it.title
                card.setOnClickListener{ view ->
                    delegator.movieSelected(it)
                }
            }
        }


    }

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle = view.findViewById<TextView>(R.id.tv_title)
        val tvReleaseDate = view.findViewById<TextView>(R.id.tv_release_date)
        val card = view.findViewById<MaterialCardView>(R.id.movie_card)
    }

    interface MovieSelector { fun movieSelected(movie: MovieBrief)}
}