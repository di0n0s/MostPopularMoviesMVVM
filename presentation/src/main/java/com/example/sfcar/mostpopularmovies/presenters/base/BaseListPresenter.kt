package com.example.sfcar.mostpopularmovies.presenters.base

interface BaseListPresenter<T> : BasePresenter {

    fun loadData()
    fun loadEndlessData()
    fun setIsLastPage(currentPage: Int, pagesNumber: Int)
    fun addFooter()
    fun showLoading()
    fun hideLoading()
    var model: ArrayList<T>
    var isLoading: Boolean
    var page: Int
    var isLastPage: Boolean
    var loadEndlessData: Boolean
}