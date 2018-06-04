package com.example.sfcar.mostpopularmovies.viewModels

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.example.sfcar.mostpopularmovies.R
import com.example.sfcar.mostpopularmovies.data.net.ApiConstants
import com.example.sfcar.mostpopularmovies.domain.interactor.params.MostPopularMoviesParams
import com.example.sfcar.mostpopularmovies.domain.interactor.params.SearchMoviesParams
import com.example.sfcar.mostpopularmovies.domain.interactor.usecase.GetPopularMoviesUseCase
import com.example.sfcar.mostpopularmovies.domain.interactor.usecase.SearchMoviesUseCase
import com.example.sfcar.mostpopularmovies.domain.model.MovieListPagination
import com.example.sfcar.mostpopularmovies.model.BaseMovieViewModel
import com.example.sfcar.mostpopularmovies.model.FooterMovieViewModel
import com.example.sfcar.mostpopularmovies.model.MovieViewModel
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

//    fun onMovieListReceived(movieList: MovieListPagination) {
//        val movieListPaginationViewModel = MovieListPaginationViewModelMapper.turnInto(movieList, view.bringContext())
//        if (!loadEndlessData) {
//            movies = movieListPaginationViewModel.movieList
//            setIsLastPage(movieListPaginationViewModel.currentPage, movieListPaginationViewModel.pagesNumber)
//            addFooter()
//        } else
//            removeFooterAndConcat(movieListPaginationViewModel)
//        setMutableLiveData()
//
//    }

    fun onMovieListReceived(movieList: MovieListPagination) {
        if (!loadEndlessData) movies.clear()
        setIsLastPage(movieList.currentPage, movieList.pagesNumber)
        movies.addAll(mapToViewModel(movieList))
        removeFooter()
        addFooter()
        setMutableLiveData()
    }

    private fun mapToViewModel(movieList: MovieListPagination) =
            movieList.movieList.map { MovieViewModel(it.title, setYearString(it.releaseDate), it.overview, setPictureUrl(it.picturePath)) }


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

//    private fun removeFooterAndConcat(movieListPaginationViewModel: MovieListPaginationViewModel) {
//        removeFooter()
//        movies.addAll(movieListPaginationViewModel.movieList)
//        if (movieListPaginationViewModel.pagesNumber != 0 && movieListPaginationViewModel.pagesNumber != movieListPaginationViewModel.currentPage)
//            addFooter()
//        else
//            isLastPage = true
//    }

    private fun removeFooter() {
        movies.removeAll { it is FooterMovieViewModel }
    }

    private fun setMutableLiveData() {
//        restartAdapter()
        model.value = movies //TODO probably this cause error on adapter
//        isLoading = false
    }

//    private fun restartAdapter() {
//        if (!loadEndlessData)
//            view.setNullAdapter()
//    }

//TODO onDestroy()??

    private fun releaseDateToYear(releaseDate: String): CharSequence =
            releaseDate.subSequence(0, 4)

    private fun setYearString(releaseDate: String): String {
        return if (releaseDate.isNotBlank())
            releaseDateToYear(releaseDate).toString()
        else
            context.getString(R.string.unknown_date) //TODO what to do with context... Inject?
    }

    private fun setPictureUrl(picturePath: String): String =
            "${ApiConstants.BASE_URL_IMAGE}${ApiConstants.IMAGE_SIZE_W342}$picturePath"


}