package com.example.sfcar.mostpopularmovies.presenters.movieDetail

import com.example.sfcar.mostpopularmovies.injector.PerFragment
import com.example.sfcar.mostpopularmovies.model.MovieDetailView
import javax.inject.Inject

@PerFragment
class MovieDetailPresenterImp @Inject constructor() : MovieDetailPresenter {

    lateinit var movieDetailView: MovieDetailView

    override fun setModel(model: MovieDetailView) {
        this.movieDetailView = model
    }

    override fun start() {}

    override fun onResume() {}

    override fun onPause() {}

    override fun onDestroy() {}

}