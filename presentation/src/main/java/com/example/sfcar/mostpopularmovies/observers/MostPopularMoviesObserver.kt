package com.example.sfcar.mostpopularmovies.observers

import com.example.sfcar.mostpopularmovies.domain.model.MovieListPagination
import com.example.sfcar.mostpopularmovies.observers.base.BaseObserver
import com.example.sfcar.mostpopularmovies.viewModels.MostPopularMoviesViewModel

class MostPopularMoviesObserver(private val viewModel: MostPopularMoviesViewModel) : BaseObserver<MovieListPagination>(viewModel) {

    override fun onNext(t: MovieListPagination) {
        viewModel.onMovieListReceived(t)
    }

}