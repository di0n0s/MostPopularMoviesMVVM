package com.example.sfcar.mostpopularmovies.presenters.base

interface BaseListPresenter<T> : BasePresenter<T> {

    fun loadData()
    fun loadEndlessData()
    fun setIsLastPage(currentPage: Int, pagesNumber: Int)
    fun addFooter()
    var page: Int
    var isLastPage: Boolean
    var loadEndlessData: Boolean
}