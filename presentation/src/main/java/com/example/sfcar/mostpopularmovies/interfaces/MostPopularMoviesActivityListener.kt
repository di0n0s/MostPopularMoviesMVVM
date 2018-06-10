package com.example.sfcar.mostpopularmovies.interfaces

import android.view.View
import com.example.sfcar.mostpopularmovies.model.MovieView

interface MostPopularMoviesActivityListener {

    fun goToMovieDetailActivity(movieView: MovieView, view: View)
}