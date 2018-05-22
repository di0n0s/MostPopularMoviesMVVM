package com.example.sfcar.mostpopularmovies.views.movieDetail

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.view.MenuItem
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.example.sfcar.mostpopularmovies.R
import com.example.sfcar.mostpopularmovies.glide.GlideApp
import com.example.sfcar.mostpopularmovies.model.MovieViewModel
import com.example.sfcar.mostpopularmovies.views.base.BaseActivity
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : BaseActivity() {

    private lateinit var viewModel: MovieViewModel

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
        initActivityTransitions()
        setContentView(R.layout.activity_movie_detail)
        setToolbarTitle()
        setToolbarImage()
        initSupportActionBar()
    }

    override fun createFragmentAndSettingTAG() {
        currentTag = MovieDetailFragment.TAG
        currentFragment = MovieDetailFragment.newInstance(getMovieExtra())
    }

    private fun getMovieExtra(): MovieViewModel {
        viewModel = intent.getParcelableExtra(EXTRA_MOVIE)
        return viewModel
    }

    override fun setToolbarTitle() {
        collapsingToolbar.title = viewModel.title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initSupportActionBar() {
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)
        }
    }

    private fun initActivityTransitions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val transition = Slide()
            transition.excludeTarget(android.R.id.statusBarBackground, true)
            window.enterTransition = transition
            window.returnTransition = transition
        }
    }

    private fun setToolbarImage() {
        if (viewModel.picturePath != "") {
            GlideApp.with(this)
                    .load(viewModel.picturePath)
                    .apply(RequestOptions().centerInside())
                    .apply(RequestOptions().placeholder(R.drawable.ic_movie_placeholder))
                    .into(movieBackdrop as ImageView)
        }
    }
}
