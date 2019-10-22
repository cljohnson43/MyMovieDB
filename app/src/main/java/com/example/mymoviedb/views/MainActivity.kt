package com.example.mymoviedb.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.mymoviedb.R
import com.example.mymoviedb.adapters.MovieAdapter
import com.example.mymoviedb.viewmodels.MoviePageViewModel

class MainActivity : AppCompatActivity() {

    val model: MoviePageViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MoviePageViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val frag = SearchDBFragment()
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, frag)
            commit()
        }
    }
}
