package com.example.sfcar.mostpopularmovies.observers

import android.content.Context
import com.example.sfcar.mostpopularmovies.domain.model.MovieListPagination
import com.example.sfcar.mostpopularmovies.observers.base.BaseObserver
import com.example.sfcar.mostpopularmovies.presenters.mostPopularMovies.MostPopularMoviesPresenterImp

class MostPopularMoviesObserver(private val presenter: MostPopularMoviesPresenterImp, context: Context) : BaseObserver<MovieListPagination>(presenter, context) {

    override fun onNext(t: MovieListPagination) {
        presenter.onMovieListReceived(t)
    }

}