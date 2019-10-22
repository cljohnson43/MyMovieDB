package com.example.mymoviedb.utils

import android.util.Log

const val DEBUG_TAG = "debug.tag"

object Logger {
    fun log(msg: String, tag: String = DEBUG_TAG) {
        Log.d(tag, msg)
    }
}