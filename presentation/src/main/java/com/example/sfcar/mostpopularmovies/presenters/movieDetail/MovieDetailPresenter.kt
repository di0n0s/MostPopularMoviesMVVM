package com.example.sfcar.mostpopularmovies.presenters.movieDetail

import com.example.sfcar.mostpopularmovies.model.MovieDetailView
import com.example.sfcar.mostpopularmovies.presenters.base.BasePresenter

interface MovieDetailPresenter : BasePresenter {

    fun setModel(model: MovieDetailView)
}