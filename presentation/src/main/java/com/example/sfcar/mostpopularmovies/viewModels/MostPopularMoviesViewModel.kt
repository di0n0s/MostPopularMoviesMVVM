package com.example.sfcar.mostpopularmovies.viewModels

import android.arch.lifecycle.MutableLiveData
import com.example.sfcar.mostpopularmovies.domain.interactor.params.MostPopularMoviesParams
import com.example.sfcar.mostpopularmovies.domain.interactor.params.SearchMoviesParams
import com.example.sfcar.mostpopularmovies.domain.interactor.usecase.GetPopularMoviesUseCase
import com.example.sfcar.mostpopularmovies.domain.interactor.usecase.SearchMoviesUseCase
import com.example.sfcar.mostpopularmovies.domain.model.MovieListPagination
import com.example.sfcar.mostpopularmovies.model.BaseMovieViewModel
import com.example.sfcar.mostpopularmovies.model.FooterMovieViewModel
import com.example.sfcar.mostpopularmovies.model.MovieViewModel
import com.example.sfcar.mostpopularmovies.model.mapper.MovieListPaginationViewModelMapper
import com.example.sfcar.mostpopularmovies.observers.MostPopularMoviesObserver
import com.example.sfcar.mostpopularmovies.viewModels.base.BaseViewModel
import javax.inject.Inject

class MostPopularMoviesViewModel @Inject constructor(private val popularMoviesUseCase: GetPopularMoviesUseCase,
                                                     private val searchMoviesUseCase: SearchMoviesUseCase) : BaseViewModel() {

    val model: MutableLiveData<ArrayList<BaseMovieViewModel>> = MutableLiveData()
    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    private var movies: ArrayList<BaseMovieViewModel> = ArrayList()
    private var page: Int = 0
    var loadEndlessData: Boolean = false
    private var query: String = ""


    override fun start() {
        loadData()
    }

    private fun getPopularMovies() {
        popularMoviesUseCase.execute(MostPopularMoviesParams(++page), MostPopularMoviesObserver(this))
    }

    private fun searchMovies(query: String) {
        searchMoviesUseCase.execute(SearchMoviesParams(++page, query), MostPopularMoviesObserver(this))
    }

    fun onMovieListReceived(movieList: MovieListPagination) {
        if (!loadEndlessData) movies.clear()
        setIsLastPage(movieList.currentPage, movieList.pagesNumber)
        movies.addAll(mapToViewModel(movieList))
        removeFooter()
        addFooter()
        setMutableLiveData()
    }

    private fun mapToViewModel(movieList: MovieListPagination) =
            movieList.movieList.map { MovieViewModel(it.title,
                    MovieListPaginationViewModelMapper().setYearString(it.releaseDate),
                    it.overview,
                    MovieListPaginationViewModelMapper().setPictureUrl(it.picturePath)) }


    private fun loadData() {
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

    fun loadEndlessData() {
        loadEndlessData = true
        selectAllMoviesOrSearch()
    }

    fun onQueryChangeListener(query: String) {
        this.query = query
        loadData()
    }

    private fun selectAllMoviesOrSearch() {
        if (query.isBlank())
            getPopularMovies()
        else
            searchMovies(query)
    }

    private fun setIsLastPage(currentPage: Int, pagesNumber: Int) {
        isLastPage = currentPage == pagesNumber
    }

    private fun addFooter() {
        if (!isLastPage)
            movies.add(FooterMovieViewModel())
    }

    private fun removeFooter() {
        movies.removeAll { it is FooterMovieViewModel }
    }

    private fun setMutableLiveData() {
        model.value = movies
    }
}