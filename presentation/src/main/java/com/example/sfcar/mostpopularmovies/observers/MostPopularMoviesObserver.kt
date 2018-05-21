package com.example.sfcar.mostpopularmovies.observers

import com.example.sfcar.mostpopularmovies.domain.model.MovieListPagination
import com.example.sfcar.mostpopularmovies.observers.base.BaseObserver
import com.example.sfcar.mostpopularmovies.presenters.mostPopularMovies.MostPopularMoviesPresenterImp

class MostPopularMoviesObserver(private val presenter: MostPopularMoviesPresenterImp) : BaseObserver<MovieListPagination>(presenter) {

    override fun onNext(t: MovieListPagination) {
        presenter.onMovieListReceived(t)
    }

}