package com.example.sfcar.mostpopularmovies.views.mostPopularMovies

interface MostPopularMoviesView {

    fun showProgressBar(show: Boolean)
    fun showRecyclerView()
    fun hideRecyclerView()
    fun hideEmptyView()
    fun showEmptyView()
    fun setRefreshingState(state: Boolean)
}