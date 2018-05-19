package com.example.sfcar.mostpopularmovies.views.mostPopularMovies

import android.os.Bundle
import com.example.sfcar.mostpopularmovies.views.base.BaseActivity
import kotlinx.android.synthetic.main.activity_base.*

class MostPopularMoviesActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)
    }

    override fun createFragmentAndSettingTAG() {
        currentFragment = MostPopularMoviesFragment.newInstance()
        currentTag = MostPopularMoviesFragment.TAG
    }
}
