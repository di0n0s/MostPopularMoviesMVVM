package com.example.sfcar.mostpopularmovies.views.base

import android.content.Context

interface BaseListView {

    fun showProgressBar(show: Boolean)
    fun showRecyclerView()
    fun hideRecyclerView()
    fun hideEmptyView()
    fun showEmptyView()
    fun setRefreshingOff()
    fun setItems()
    fun bringContext(): Context
}