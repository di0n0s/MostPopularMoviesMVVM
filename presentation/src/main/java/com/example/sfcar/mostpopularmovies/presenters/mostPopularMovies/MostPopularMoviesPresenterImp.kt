package com.example.sfcar.mostpopularmovies.presenters.mostPopularMovies

import com.example.sfcar.mostpopularmovies.domain.interactor.params.MostPopularMoviesParams
import com.example.sfcar.mostpopularmovies.domain.interactor.params.SearchMoviesParams
import com.example.sfcar.mostpopularmovies.domain.interactor.usecase.GetPopularMoviesUseCase
import com.example.sfcar.mostpopularmovies.domain.interactor.usecase.SearchMoviesUseCase
import com.example.sfcar.mostpopularmovies.domain.model.MovieListPagination
import com.example.sfcar.mostpopularmovies.injector.PerFragment
import com.example.sfcar.mostpopularmovies.model.BaseMovieViewModel
import com.example.sfcar.mostpopularmovies.model.FooterMovieViewModel
import com.example.sfcar.mostpopularmovies.model.MovieListPaginationViewModel
import com.example.sfcar.mostpopularmovies.model.enumerations.ErrorEnum
import com.example.sfcar.mostpopularmovies.model.mapper.MovieListPaginationViewModelMapper
import com.example.sfcar.mostpopularmovies.observers.MostPopularMoviesObserver
import com.example.sfcar.mostpopularmovies.views.mostPopularMovies.MostPopularMoviesView
import javax.inject.Inject

@PerFragment
class MostPopularMoviesPresenterImp @Inject constructor(private val popularMoviesUseCase: GetPopularMoviesUseCase,
                                                        private val searchMoviesUseCase: SearchMoviesUseCase) : MostPopularMoviesPresenter<BaseMovieViewModel> {

    override var model: ArrayList<BaseMovieViewModel> = ArrayList()
    override var page: Int = 0
    override var isLastPage: Boolean = false
    override var isLoading: Boolean = false
    override var query: String = ""
    @Inject
    lateinit var view: MostPopularMoviesView
    private var loadEndlessData: Boolean = false

    override fun start() {
        loadData()
    }

    private fun getPopularMovies() {
        popularMoviesUseCase.execute(MostPopularMoviesParams(++page), MostPopularMoviesObserver(this))
    }

    private fun searchMovies(query: String) {
        searchMoviesUseCase.execute(SearchMoviesParams(++page, query), MostPopularMoviesObserver(this))
    }

    override fun onMovieListReceived(movieList: MovieListPagination) {
        val movieListPaginationViewModel = MovieListPaginationViewModelMapper.turnInto(movieList, view.bringContext())
        if (!loadEndlessData) {
            model = movieListPaginationViewModel.movieList
            setIsLastPage(movieListPaginationViewModel.currentPage, movieListPaginationViewModel.pagesNumber)
            addFooter()
        } else
            removeFooterAndConcat(movieListPaginationViewModel)
        manageViewAfterOK()

    }

    override fun onErrorReceived(errorCode: Int) {
        view.showEmptyView()
        view.hideRecyclerView()
        view.showProgressBar(false)
        view.showErrorMessage(ErrorEnum.findErrorDescriptionByErrorCode(errorCode, view.bringContext()))

    }

    override fun loadData() {
        setPageZero()
        setLoadEndlessDataFalse()
        selectAllMoviesOrSearch()
    }

    private fun setLoadEndlessDataFalse() {
        loadEndlessData = false
    }

    private fun setPageZero() {
        page = 0
    }

    override fun loadEndlessData() {
        loadEndlessData = true
        selectAllMoviesOrSearch()
    }

    override fun onQueryChangeListener(query: String) {
        this.query = query
        loadData()
    }

    private fun selectAllMoviesOrSearch() {
        if (query.isBlank())
            getPopularMovies()
        else
            searchMovies(query)
    }

    override fun setIsLastPage(currentPage: Int, pagesNumber: Int) {
        isLastPage = currentPage == pagesNumber
    }

    override fun addFooter() {
        if (!isLastPage)
            model.add(FooterMovieViewModel())
    }

    private fun removeFooterAndConcat(movieListPaginationViewModel: MovieListPaginationViewModel) {
        model.removeAll { it is FooterMovieViewModel }
        model.addAll(movieListPaginationViewModel.movieList)
        if (movieListPaginationViewModel.pagesNumber != 0 && movieListPaginationViewModel.pagesNumber != movieListPaginationViewModel.currentPage)
            addFooter()
        else
            isLastPage = true
    }

    private fun manageViewAfterOK() {
        restartAdapter()
        view.setItems()
        showOrHideEmptyAndRecyclerView()
        isLoading = false
        view.showProgressBar(false)
    }

    private fun restartAdapter() {
        if (!loadEndlessData)
            view.setNullAdapter()
    }

    private fun showOrHideEmptyAndRecyclerView() {
        if (model.isEmpty()) {
            view.showEmptyView()
            view.hideRecyclerView()
        } else {
            view.showRecyclerView()
            view.hideEmptyView()
        }
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onDestroy() {
        popularMoviesUseCase.dispose()
        searchMoviesUseCase.dispose()
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

}