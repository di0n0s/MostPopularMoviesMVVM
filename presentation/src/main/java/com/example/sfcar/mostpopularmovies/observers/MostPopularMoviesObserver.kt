package com.example.sfcar.mostpopularmovies.observers

import com.example.sfcar.mostpopularmovies.domain.model.MovieListPagination
import com.example.sfcar.mostpopularmovies.presenters.mostPopularMovies.MostPopularMoviesPresenterImp
import io.reactivex.observers.DisposableObserver

class MostPopularMoviesObserver(private val presenter: MostPopularMoviesPresenterImp) : DisposableObserver<MovieListPagination>() {

    override fun onComplete() {
    }

    override fun onNext(t: MovieListPagination) {
        presenter.onMovieListReceived(t)
    }

    override fun onError(e: Throwable) {
        presenter.hideLoading()
        presenter.onErrorReceived()
    }

}