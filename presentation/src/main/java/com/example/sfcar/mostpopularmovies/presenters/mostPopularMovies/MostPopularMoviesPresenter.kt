package com.example.sfcar.mostpopularmovies.presenters.mostPopularMovies

import com.example.sfcar.mostpopularmovies.domain.model.MovieListPagination
import com.example.sfcar.mostpopularmovies.presenters.base.BaseListPresenter

interface MostPopularMoviesPresenter<T> : BaseListPresenter<T> {

    fun getPopularMovies()
    fun onMovieListReceived(movieList: MovieListPagination)
    fun onErrorReceived(message: String?)
}