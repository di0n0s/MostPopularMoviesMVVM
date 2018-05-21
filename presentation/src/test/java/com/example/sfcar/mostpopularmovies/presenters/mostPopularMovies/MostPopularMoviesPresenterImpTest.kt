package com.example.sfcar.mostpopularmovies.presenters.mostPopularMovies

import com.example.sfcar.mostpopularmovies.UnitTest
import com.example.sfcar.mostpopularmovies.domain.interactor.usecase.GetPopularMoviesUseCase
import com.example.sfcar.mostpopularmovies.model.BaseMovieViewModel
import com.example.sfcar.mostpopularmovies.views.mostPopularMovies.MostPopularMoviesView
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock


class MostPopularMoviesPresenterImpTest : UnitTest() {

    private lateinit var presenter: MostPopularMoviesPresenterImp
    @Mock
    private lateinit var useCase: GetPopularMoviesUseCase
    @Mock
    private lateinit var view: MostPopularMoviesView
    @Mock
    private lateinit var model: ArrayList<BaseMovieViewModel>

    @Before
    fun setup() {
        presenter = MostPopularMoviesPresenterImp(useCase)
        initView()
    }

    private fun initView() {
        presenter.view = view
    }

//    @Test
//    fun getPopularMoviesTest() {
//        presenter.getPopularMovies()
//
//        verify(useCase).execute(any(), any())
//    }

    @Test
    fun onErrorReceivedTest() {
        presenter.onErrorReceived("any")

        verify(presenter.view).showEmptyView()
        verify(presenter.view).hideRecyclerView()
        verify(presenter.view).showProgressBar(false)
        verify(presenter.view).showErrorMessage("any")
    }

//    @Test
//    fun loadDataTest() {
//        presenter.loadData()
//
//        assert(presenter.page == 0)
//        assert(!presenter.loadEndlessData)
//        verify(useCase).execute(any(), any())
//    }

//    @Test
//    fun loadEndlessDataTest() {
//        presenter.loadEndlessData()
//        assert(presenter.loadEndlessData)
//        verify(useCase).execute(any(), any())
//    }

    @Test
    fun setIsLastPageTrueTest() {
        presenter.setIsLastPage(23, 23)
        assert(presenter.isLastPage)
    }

    @Test
    fun setIsLastPageFalseTest() {
        presenter.setIsLastPage(10, 12)
        assert(!presenter.isLastPage)
    }

    @Test
    fun addFooterFalseTest() {
        presenter.isLastPage = true

        presenter.addFooter()

        verify(model, never()).add(any())
    }

//    @Test
//    fun addFooterTrueTest() {
//        presenter.isLastPage = false
//
//        presenter.addFooter()
//
//        verify(model).add(any())
//    }
}