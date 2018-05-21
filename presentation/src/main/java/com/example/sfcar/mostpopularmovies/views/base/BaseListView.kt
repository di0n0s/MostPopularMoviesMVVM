package com.example.sfcar.mostpopularmovies.views.base

import android.content.Context

interface BaseListView {

    fun showProgressBar(show: Boolean)
    fun showRecyclerView()
    fun hideRecyclerView()
    fun hideEmptyView()
    fun showEmptyView()
    fun setItems()
    fun setNullAdapter()
    fun bringContext(): Context
    fun setRefreshingBehaviour()
    fun setEmptyView()
    fun showErrorMessage(message: String?)
}