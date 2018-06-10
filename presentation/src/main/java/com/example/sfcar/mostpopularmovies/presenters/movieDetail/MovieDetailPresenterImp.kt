package com.example.sfcar.mostpopularmovies.presenters.movieDetail

import com.example.sfcar.mostpopularmovies.injector.PerFragment
import com.example.sfcar.mostpopularmovies.model.MovieView
import javax.inject.Inject

@PerFragment
class MovieDetailPresenterImp @Inject constructor() : MovieDetailPresenter {

    lateinit var movieView: MovieView

    override fun setModel(model: MovieView) {
        this.movieView = model
    }

    override fun start() {}

    override fun onResume() {}

    override fun onPause() {}

    override fun onDestroy() {}

}