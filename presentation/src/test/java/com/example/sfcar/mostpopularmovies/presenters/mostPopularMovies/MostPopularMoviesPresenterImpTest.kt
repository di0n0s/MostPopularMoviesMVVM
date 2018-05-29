package com.example.sfcar.mostpopularmovies.presenters.mostPopularMovies

import com.example.sfcar.mostpopularmovies.UnitTest
import com.example.sfcar.mostpopularmovies.domain.interactor.usecase.GetPopularMoviesUseCase
import com.example.sfcar.mostpopularmovies.domain.interactor.usecase.SearchMoviesUseCase
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
    private lateinit var movieUseCase: GetPopularMoviesUseCase
    @Mock
    private lateinit var searchUseCase : SearchMoviesUseCase
    @Mock
    private lateinit var view: MostPopularMoviesView
    @Mock
    private lateinit var model: ArrayList<BaseMovieViewModel>

    @Before
    fun setup() {
        presenter = MostPopularMoviesPresenterImp(movieUseCase, searchUseCase)
        initView()
    }

    private fun initView() {
        presenter.view = view
    }

    @Test
    fun getPopularMoviesTest() {
        presenter.getPopularMovies()

        verify(movieUseCase).execute(any(), any())
    }

    @Test
    fun loadDataTest() {
        presenter.loadData()

        assert(presenter.page == 1)
        assert(!presenter.loadEndlessData)
        verify(movieUseCase).execute(any(), any())
    }

    @Test
    fun loadEndlessDataTest() {
        presenter.loadEndlessData()
        assert(presenter.loadEndlessData)
        verify(movieUseCase).execute(any(), any())
    }

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
}