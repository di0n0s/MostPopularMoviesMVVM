package com.example.sfcar.mostpopularmovies.navigation

import android.content.Context
import android.os.Bundle
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.sfcar.mostpopularmovies.R
import com.example.sfcar.mostpopularmovies.model.MovieDetailView
import com.example.sfcar.mostpopularmovies.model.MovieView
import com.example.sfcar.mostpopularmovies.views.movieDetail.MovieDetailActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Navigator @Inject constructor() {

    fun toMovieDetail(context: Context?, movieView: MovieView, view: View) {
        if (context != null) {
            val intentToLaunch = MovieDetailActivity.getCallingIntent(context, movieView)
            context.startActivity(intentToLaunch, prepareOptionsToBundle(context, view))
        }
    }

    private fun prepareOptionsToBundle(context: Context?, view: android.view.View): Bundle? {
        val transitionName = context?.getString(R.string.movie_transition_poster)
        val options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(context as AppCompatActivity, view, transitionName!!)
        return options.toBundle()
    }
}