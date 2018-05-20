package com.example.sfcar.mostpopularmovies.views.mostPopularMovies


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sfcar.mostpopularmovies.MostPopularMoviesApplication
import com.example.sfcar.mostpopularmovies.R
import com.example.sfcar.mostpopularmovies.adapters.MostPopularMoviesAdapter
import com.example.sfcar.mostpopularmovies.injector.modules.BaseFragmentModule
import com.example.sfcar.mostpopularmovies.injector.modules.BaseListModule
import com.example.sfcar.mostpopularmovies.injector.modules.MostPopularMoviesModule
import com.example.sfcar.mostpopularmovies.model.BaseMovieViewModel
import com.example.sfcar.mostpopularmovies.model.enumerations.EmptyViewModel
import com.example.sfcar.mostpopularmovies.presenters.MostPopularMoviesPresenterImp
import com.example.sfcar.mostpopularmovies.views.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_most_popular_movies.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class MostPopularMoviesFragment : BaseFragment(), MostPopularMoviesView {

    @Inject
    lateinit var presenter: MostPopularMoviesPresenterImp
    @Inject
    lateinit var layoutManager: StaggeredGridLayoutManager

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
        setRecyclerView()
        presenter.start()

    }

    override fun setupFragmentComponent() {
        MostPopularMoviesApplication
                .applicationComponent
                .plus(BaseFragmentModule(this.context!!), BaseListModule(this.context!!), MostPopularMoviesModule(this))
                .inject(this)
    }

    override fun bringContext(): Context = this.context!!

    override fun setItems() {
        setAdapter(presenter.model)
    }

    override fun showProgressBar(show: Boolean) {
        if (swipeRefreshLayout.isRefreshing) {
            if (!show)
                swipeRefreshLayout.isRefreshing = false
        } else progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showRecyclerView() {
        mostPopularMoviesRecyclerView.visibility = View.VISIBLE
    }

    override fun hideRecyclerView() {
        mostPopularMoviesRecyclerView.visibility = View.GONE
    }

    override fun hideEmptyView() {
        emptyView.visibility = View.GONE
    }

    override fun showEmptyView() {
        emptyView.visibility = View.VISIBLE
    }

    override fun setRefreshingOff() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun setEmptyView() {
        emptyView.fillViews(EmptyViewModel.EMPTY_MOST_POPULAR_MOVIES)
    }

    private fun setRecyclerView() {
        setLayoutManager()
        setScrollListener()
    }

    private fun setAdapter(model: List<BaseMovieViewModel>) {
        initAdapter(model)
        notifyDataSetChanged()
    }

    private fun notifyDataSetChanged() {
        mostPopularMoviesRecyclerView.adapter.notifyDataSetChanged()
    }

    private fun initAdapter(model: List<BaseMovieViewModel>) {
        if (mostPopularMoviesRecyclerView.adapter == null)
            mostPopularMoviesRecyclerView.adapter = MostPopularMoviesAdapter(model)
    }

    private fun setScrollListener() {
        mostPopularMoviesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                var pastVisibleItems = 0
                var firstVisibleItems: IntArray? = null
                firstVisibleItems = layoutManager.findFirstVisibleItemPositions(firstVisibleItems)
                if (firstVisibleItems != null && firstVisibleItems.isNotEmpty()) {
                    pastVisibleItems = firstVisibleItems[0]
                }
                if (!presenter.isLastPage && !presenter.isLoading) {
                    if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                        presenter.isLoading = true
                        presenter.loadEndlessData()
                    }

                }
            }
        })
    }

    private fun setLayoutManager() {
        mostPopularMoviesRecyclerView.layoutManager = layoutManager
    }
}
