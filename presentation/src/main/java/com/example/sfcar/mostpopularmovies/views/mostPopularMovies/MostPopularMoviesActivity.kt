package com.example.sfcar.mostpopularmovies.views.mostPopularMovies

import com.example.sfcar.mostpopularmovies.views.base.BaseActivity

class MostPopularMoviesActivity : BaseActivity() {

    override fun createFragmentAndSettingTAG() {
        currentFragment = MostPopularMoviesFragment.newInstance()
        currentTag = MostPopularMoviesFragment.TAG
    }
}
