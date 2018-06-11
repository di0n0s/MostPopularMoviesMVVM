package com.example.sfcar.mostpopularmovies.views.mostPopularMovies

import android.os.Bundle
import android.view.View
import com.example.sfcar.mostpopularmovies.R
import com.example.sfcar.mostpopularmovies.interfaces.MostPopularMoviesActivityListener
import com.example.sfcar.mostpopularmovies.model.MovieDetailView
import com.example.sfcar.mostpopularmovies.model.MovieView
import com.example.sfcar.mostpopularmovies.views.base.BaseActivity
import kotlinx.android.synthetic.main.activity_base.*

class MostPopularMoviesActivity : BaseActivity(), MostPopularMoviesActivityListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbarTitle()
    }

    override fun setToolbarTitle() {
        toolbar.setTitle(R.string.most_popular_movies_toolbar_title)
    }

    override fun createFragmentAndSettingTAG() {
        currentFragment = MostPopularMoviesFragment.newInstance()
        currentTag = MostPopularMoviesFragment.TAG
    }

    override fun goToMovieDetailActivity(movieView: MovieView, view: View) {
        navigator.toMovieDetail(this, movieView, view)
    }
}
