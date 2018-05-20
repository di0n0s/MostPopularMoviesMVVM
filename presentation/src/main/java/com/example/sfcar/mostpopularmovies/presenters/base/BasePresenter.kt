package com.example.sfcar.mostpopularmovies.presenters.base

interface BasePresenter {

    fun start()
    fun onResume()
    fun onPause()
    fun onDestroy()

}