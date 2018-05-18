package com.example.sfcar.mostpopularmovies

import android.app.Application

class MostPopularMoviesApplication : Application() {

    companion object {
        lateinit var instance : MostPopularMoviesApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}