package com.example.sfcar.mostpopularmovies.interfaces

import android.view.View
import com.example.sfcar.mostpopularmovies.model.MovieViewModel

interface MostPopularMoviesActivityListener {

    fun goToMovieDetailActivity(movieViewModel: MovieViewModel, view: View)
}