package com.example.sfcar.mostpopularmovies.views.mostPopularMovies


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sfcar.mostpopularmovies.MostPopularMoviesApplication

import com.example.sfcar.mostpopularmovies.R
import com.example.sfcar.mostpopularmovies.injector.modules.BaseFragmentModule
import com.example.sfcar.mostpopularmovies.injector.modules.BaseListModule
import com.example.sfcar.mostpopularmovies.injector.modules.MostPopularMoviesModule
import com.example.sfcar.mostpopularmovies.presenters.MostPopularMoviesPresenterImp
import com.example.sfcar.mostpopularmovies.views.base.BaseFragment
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class MostPopularMoviesFragment : BaseFragment(), MostPopularMoviesView {

    @Inject
    lateinit var presenter: MostPopularMoviesPresenterImp

    companion object {
        fun newInstance() = MostPopularMoviesFragment()
        const val TAG = "MostPopularMoviesFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_most_popular_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()

    }

    override fun setupFragmentComponent() {
        MostPopularMoviesApplication
                .applicationComponent
                .plus(BaseFragmentModule(this.context!!), BaseListModule(this.context!!), MostPopularMoviesModule(this))
                .inject(this)
    }

    override fun showProgressBar(show: Boolean) {
    }

    override fun showRecyclerView() {
        showToastMessage("OK")
    }

    override fun hideRecyclerView() {
    }

    override fun hideEmptyView() {
    }

    override fun showEmptyView() {
    }

    override fun setRefreshingState(state: Boolean) {
    }
}
