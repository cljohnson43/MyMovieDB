package com.example.mymoviedb.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mymoviedb.R

class MainActivity : AppCompatActivity() {

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
