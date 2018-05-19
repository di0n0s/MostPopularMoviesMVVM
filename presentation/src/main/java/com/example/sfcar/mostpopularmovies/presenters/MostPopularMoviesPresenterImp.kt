package com.example.sfcar.mostpopularmovies.presenters

import com.example.sfcar.mostpopularmovies.domain.interactor.params.MostPopularMoviesParams
import com.example.sfcar.mostpopularmovies.domain.interactor.usecase.GetPopularMoviesUseCase
import com.example.sfcar.mostpopularmovies.domain.model.MovieListPagination
import com.example.sfcar.mostpopularmovies.injector.PerFragment
import com.example.sfcar.mostpopularmovies.model.FooterMovieViewModel
import com.example.sfcar.mostpopularmovies.model.MovieListPaginationViewModel
import com.example.sfcar.mostpopularmovies.model.MovieViewModel
import com.example.sfcar.mostpopularmovies.model.mapper.MovieListPaginationViewModelMapper
import com.example.sfcar.mostpopularmovies.observers.MostPopularMoviesObserver
import com.example.sfcar.mostpopularmovies.views.mostPopularMovies.MostPopularMoviesView
import javax.inject.Inject

@PerFragment
class MostPopularMoviesPresenterImp @Inject constructor(private val useCase: GetPopularMoviesUseCase) : MostPopularMoviesPresenter<MovieViewModel> {

    override var model: ArrayList<MovieViewModel> = ArrayList()
    override var page: Int = 1
    override var loadEndlessData: Boolean = false
    override var isLastPage: Boolean = false
    @Inject
    lateinit var view: MostPopularMoviesView

    override fun start() {
        loadData()
    }

    override fun getPopularMovies() {
        useCase.execute(MostPopularMoviesParams(page), MostPopularMoviesObserver(this))
    }

    override fun onMovieListReceived(movieList: MovieListPagination) {
        val movieListPaginationViewModel = MovieListPaginationViewModelMapper.turnInto(movieList)
        if (!loadEndlessData) {
            model = movieListPaginationViewModel.movieList
            setIsLastPage(movieListPaginationViewModel.currentPage, movieListPaginationViewModel.pagesNumber)
            addFooter()
        } else
            removeFooterAndConcat(movieListPaginationViewModel)
        manageViewAfterOK()

    }

    override fun onErrorReceived() {
        view.showEmptyView()
        view.hideRecyclerView()
        view.setRefreshingState(false)
        view.showProgressBar(false)
    }

    override fun loadData() {
        page = 1
        loadEndlessData = false
        getPopularMovies()
    }

    override fun loadEndlessData() {
        loadEndlessData = true
        getPopularMovies()
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
        if (movieListPaginationViewModel.pagesNumber != 1 && movieListPaginationViewModel.pagesNumber != movieListPaginationViewModel.currentPage)
            addFooter()
        else
            isLastPage = true
    }

    private fun manageViewAfterOK() {
        showOrHideEmptyAndRecyclerView()
        view.setRefreshingState(false)
        view.showProgressBar(false)
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
        useCase.dispose()
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

}