package com.example.sfcar.mostpopularmovies.presenters.base

interface BaseApiPresenter : BasePresenter {

    fun showLoading()
    fun hideLoading()
    fun onErrorReceived(message: String?)

}