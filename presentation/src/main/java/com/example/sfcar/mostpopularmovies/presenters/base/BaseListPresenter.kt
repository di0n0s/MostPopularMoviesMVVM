package com.example.sfcar.mostpopularmovies.presenters.base

interface BaseListPresenter<T> : BaseApiPresenter {

    fun loadData()
    fun loadEndlessData()
    fun setIsLastPage(currentPage: Int, pagesNumber: Int)
    fun addFooter()
    var model: ArrayList<T>
    var isLoading: Boolean
    var page: Int
    var isLastPage: Boolean
}