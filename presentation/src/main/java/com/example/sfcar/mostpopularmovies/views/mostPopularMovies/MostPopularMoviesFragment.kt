package com.example.sfcar.mostpopularmovies.views.mostPopularMovies


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sfcar.mostpopularmovies.MostPopularMoviesApplication
import com.example.sfcar.mostpopularmovies.R
import com.example.sfcar.mostpopularmovies.adapters.MostPopularMoviesAdapter
import com.example.sfcar.mostpopularmovies.injector.modules.BaseFragmentModule
import com.example.sfcar.mostpopularmovies.injector.modules.BaseListModule
import com.example.sfcar.mostpopularmovies.injector.modules.MostPopularMoviesModule
import com.example.sfcar.mostpopularmovies.interfaces.AdapterListOnClickListener
import com.example.sfcar.mostpopularmovies.interfaces.MostPopularMoviesActivityListener
import com.example.sfcar.mostpopularmovies.model.BaseMovieViewModel
import com.example.sfcar.mostpopularmovies.model.MovieViewModel
import com.example.sfcar.mostpopularmovies.model.enumerations.EmptyViewModel
import com.example.sfcar.mostpopularmovies.presenters.mostPopularMovies.MostPopularMoviesPresenterImp
import com.example.sfcar.mostpopularmovies.views.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_most_popular_movies.*
import rx.Subscription
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class MostPopularMoviesFragment : BaseFragment(), MostPopularMoviesView, AdapterListOnClickListener.ViewListener {

    @Inject
    lateinit var presenter: MostPopularMoviesPresenterImp
    @Inject
    lateinit var layoutManager: GridLayoutManager
    @Inject
    lateinit var activityListener: MostPopularMoviesActivityListener
    private var subscription: Subscription? = null

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
        setSpanSize()
        setEmptyView()
        setRefreshingBehaviour()
        setSearchListener()
        presenter.start()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mostPopularMoviesRecyclerView.adapter = null
    }

    override fun onDestroy() {
        super.onDestroy()
        subscription?.unsubscribe()
        presenter.onDestroy()
    }

    override fun setupFragmentComponent() {
        MostPopularMoviesApplication
                .applicationComponent
                .plus(BaseFragmentModule(this.context!!),
                        BaseListModule(this.context!!),
                        MostPopularMoviesModule(this, activity as MostPopularMoviesActivity))
                .inject(this)
    }

    override fun showErrorMessage(message: String?) {
        message?.let { showToastMessage(it) }
    }

    override fun bringContext(): Context = this.context!!

    override fun setItems() {
        setAdapter(presenter.model)
    }

    override fun showProgressBar(show: Boolean) {
        if (swipeRefreshLayout.isRefreshing) {
            if (!show)
                swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun setRefreshingBehaviour() {
        swipeRefreshLayout.setOnRefreshListener {
            presenter.isLoading = true
            presenter.start()
        }
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

    override fun setEmptyView() {
        emptyView.fillViews(EmptyViewModel.EMPTY_MOST_POPULAR_MOVIES)
    }

    override fun onItemSelected(position: Int, view: View) {
        activityListener.goToMovieDetailActivity(presenter.model[position] as MovieViewModel, view)
    }

    private fun setRecyclerView() {
        setLayoutManager()
        setScrollListener()
    }

    override fun setNullAdapter() {
        mostPopularMoviesRecyclerView.adapter = null
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
            mostPopularMoviesRecyclerView.adapter = MostPopularMoviesAdapter(model, this)
    }

    private fun setScrollListener() {
        mostPopularMoviesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0) {
                    val lastItem = layoutManager.findLastCompletelyVisibleItemPosition()
                    val currentTotalCount = layoutManager.itemCount
                    if (currentTotalCount <= lastItem + layoutManager.spanCount) {
                        if (!presenter.isLastPage && !presenter.isLoading) {
                            presenter.isLoading = true
                            presenter.loadEndlessData()
                        }
                    }
                } else if (layoutManager.findLastVisibleItemPosition() == recyclerView?.adapter?.itemCount!! - 1
                        && !presenter.isLastPage) {
                    presenter.loadEndlessData()
                    presenter.isLoading = true
                }
            }
        })
    }

    private fun setSpanSize() {
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val adapter = mostPopularMoviesRecyclerView.adapter
                return when (adapter.getItemViewType(position)) {
                    MostPopularMoviesAdapter.MOVIE_TYPE -> 1
                    MostPopularMoviesAdapter.FOOTER_TYPE -> 3
                    else -> -1
                }
            }

        }
    }

    private fun setLayoutManager() {
        mostPopularMoviesRecyclerView.layoutManager = layoutManager
    }

    private fun setSearchListener() {
        movieSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                presenter.onQueryChangeListener(newText.toString())
                return false
            }

        })
    }
}
