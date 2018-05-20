package com.example.sfcar.mostpopularmovies.views.movieDetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.sfcar.mostpopularmovies.R
import com.example.sfcar.mostpopularmovies.model.MovieViewModel
import com.example.sfcar.mostpopularmovies.views.base.BaseActivity

class MovieDetailActivity : BaseActivity() {

    companion object {
        private const val EXTRA_MOVIE = "ExtraMovie"

        fun getCallingIntent(context: Context, movieViewModel: MovieViewModel): Intent {
            val callingIntent = Intent(context, MovieDetailActivity::class.java)
            callingIntent.putExtra(EXTRA_MOVIE, movieViewModel)
            return callingIntent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
    }

    override fun createFragmentAndSettingTAG() {
        currentTag = MovieDetailFragment.TAG
        currentFragment = MovieDetailFragment.newInstance(getMovieExtra())
    }

    private fun getMovieExtra(): MovieViewModel = intent.getParcelableExtra(EXTRA_MOVIE)

}
