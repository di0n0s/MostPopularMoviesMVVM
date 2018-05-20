package com.example.sfcar.mostpopularmovies.presenters.movieDetail

import com.example.sfcar.mostpopularmovies.injector.PerFragment
import com.example.sfcar.mostpopularmovies.model.MovieViewModel
import javax.inject.Inject

@PerFragment
class MovieDetailPresenterImp @Inject constructor() : MovieDetailPresenter {

    lateinit var movieViewModel: MovieViewModel

    override fun setModel(model: MovieViewModel) {
        this.movieViewModel = model
    }

    override fun start() {}

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onDestroy() {
    }

}