package com.example.sfcar.mostpopularmovies.presenters.base

interface BasePresenter<T> {

    fun start()
    fun onResume()
    fun onPause()
    fun onDestroy()
    fun showLoading()
    fun hideLoading()
    var model: ArrayList<T>
}