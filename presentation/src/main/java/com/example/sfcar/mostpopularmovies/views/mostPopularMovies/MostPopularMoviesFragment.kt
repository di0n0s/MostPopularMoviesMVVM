package com.example.sfcar.mostpopularmovies.views.mostPopularMovies


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.sfcar.mostpopularmovies.MostPopularMoviesApplication
import com.example.sfcar.mostpopularmovies.R
import com.example.sfcar.mostpopularmovies.adapters.MostPopularMoviesAdapter
import com.example.sfcar.mostpopularmovies.extensions.failure
import com.example.sfcar.mostpopularmovies.extensions.observe
import com.example.sfcar.mostpopularmovies.extensions.viewModel
import com.example.sfcar.mostpopularmovies.injector.modules.BaseFragmentModule
import com.example.sfcar.mostpopularmovies.injector.modules.BaseListModule
import com.example.sfcar.mostpopularmovies.injector.modules.MostPopularMoviesModule
import com.example.sfcar.mostpopularmovies.interfaces.AdapterListOnClickListener
import com.example.sfcar.mostpopularmovies.interfaces.MostPopularMoviesActivityListener
import com.example.sfcar.mostpopularmovies.model.BaseMovieView
import com.example.sfcar.mostpopularmovies.model.MovieView
import com.example.sfcar.mostpopularmovies.model.enumerations.EmptyViewModel
import com.example.sfcar.mostpopularmovies.model.enumerations.ErrorEnum
import com.example.sfcar.mostpopularmovies.viewModels.MostPopularMoviesViewModel
import com.example.sfcar.mostpopularmovies.views.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_most_popular_movies.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class MostPopularMoviesFragment : BaseFragment(), AdapterListOnClickListener.ViewListener {

    @Inject
    lateinit var layoutManager: GridLayoutManager
    @Inject
    lateinit var activityListener: MostPopularMoviesActivityListener
    private lateinit var viewModel: MostPopularMoviesViewModel


    companion object {
        fun newInstance() = MostPopularMoviesFragment()
        const val TAG = "MostPopularMoviesFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModel(viewModelFactory) {
            observe(model, ::renderMoviesList)
            failure(failure, ::handleFailure)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_most_popular_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        setEmptyView()
        setRefreshingBehaviour()
        setSearchListener()
        setSpanSize()
        viewModel.start()
    }

    override fun onResume() {
        super.onResume()
        searchViewLinearLayout.requestFocus()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        setNullAdapter()
    }

    override fun setupFragmentComponent() {
        MostPopularMoviesApplication
                .applicationComponent
                .plus(BaseFragmentModule(this.context!!),
                        BaseListModule(this.context!!),
                        MostPopularMoviesModule(activity as MostPopularMoviesActivity))
                .inject(this)
    }

    private fun showErrorMessage(message: String?) {
        message?.let { showToastMessage(it) }
    }

    private fun restartLastPosition() {
        (mostPopularMoviesRecyclerView.adapter as MostPopularMoviesAdapter).restartLastPosition()
    }

    private fun showProgressBar(show: Boolean) {
        if (swipeRefreshLayout.isRefreshing) {
            if (!show)
                swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun setRefreshingBehaviour() {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.isLoading = true
            restartLastPosition()
            viewModel.start()
        }
    }

    private fun showRecyclerView() {
        mostPopularMoviesRecyclerView.visibility = View.VISIBLE
    }

    private fun hideRecyclerView() {
        mostPopularMoviesRecyclerView.visibility = View.GONE
    }

    private fun hideEmptyView() {
        emptyView.visibility = View.GONE
    }

    private fun showEmptyView() {
        emptyView.visibility = View.VISIBLE
    }

    private fun setEmptyView() {
        emptyView.fillViews(EmptyViewModel.EMPTY_MOST_POPULAR_MOVIES)
    }

    override fun onItemSelected(position: Int, view: View) {
        activityListener.goToMovieDetailActivity(viewModel.model.value?.get(position) as MovieView, view)
    }

    private fun renderMoviesList(movies: List<BaseMovieView>?) {
        restartAdapter()
        movies?.let { setAdapter(it) }
        showOrHideEmptyAndRecyclerView()
        viewModel.isLoading = false
        showProgressBar(false)
    }

    private fun handleFailure(errorCode: Int?) {
        showEmptyView()
        hideRecyclerView()
        showProgressBar(false)
        showErrorMessage(ErrorEnum.findErrorDescriptionByErrorCode(errorCode!!, this.context!!))
    }

    private fun showOrHideEmptyAndRecyclerView() {
        if (viewModel.model.value?.isEmpty()!!) {
            showEmptyView()
            hideRecyclerView()
        } else {
            showRecyclerView()
            hideEmptyView()
        }
    }

    private fun restartAdapter() {
        if (!viewModel.loadEndlessData)
            setNullAdapter()
    }

    private fun setRecyclerView() {
        setLayoutManager()
        setScrollListener()
    }

    private fun setNullAdapter() {
        mostPopularMoviesRecyclerView.adapter = null
    }

    private fun setAdapter(model: List<BaseMovieView>) {
        initAdapter(model)
        notifyDataSetChanged()
    }

    private fun notifyDataSetChanged() {
        mostPopularMoviesRecyclerView.adapter.notifyDataSetChanged()
    }

    private fun initAdapter(model: List<BaseMovieView>) {
        if (mostPopularMoviesRecyclerView.adapter == null)
            mostPopularMoviesRecyclerView.adapter = MostPopularMoviesAdapter(model, this)
    }

    private fun setScrollListener() {
        mostPopularMoviesRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0) {
                    val lastItem = layoutManager.findLastCompletelyVisibleItemPosition()
                    val currentTotalCount = layoutManager.itemCount
                    //6 elements before to call endless data
                    if (currentTotalCount <= lastItem + 6) {
                        if (!viewModel.isLastPage && !viewModel.isLoading) {
                            viewModel.isLoading = true
                            viewModel.loadEndlessData()
                        }
                    }
                }
            }
        })
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
                viewModel.onQueryChangeListener(newText.toString())
                return false
            }

        })

        movieSearchView.findViewById<ImageView>(R.id.search_close_btn).setOnClickListener {
            movieSearchView.setQuery("", false)
            movieSearchView.clearFocus()
        }

    }

    private fun setSpanSize() {
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val adapter = mostPopularMoviesRecyclerView.adapter
                if (position < adapter.itemCount) {
                    return when (adapter.getItemViewType(position)) {
                        MostPopularMoviesAdapter.MOVIE_TYPE -> 1
                        MostPopularMoviesAdapter.FOOTER_TYPE -> 3
                        else -> -1
                    }
                }
                return -1
            }

        }
    }
}
