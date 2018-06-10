package com.example.sfcar.mostpopularmovies.viewModels

import com.example.sfcar.mostpopularmovies.UnitTest
import com.example.sfcar.mostpopularmovies.domain.interactor.usecase.GetPopularMoviesUseCase
import com.example.sfcar.mostpopularmovies.domain.interactor.usecase.SearchMoviesUseCase
import com.example.sfcar.mostpopularmovies.model.BaseMovieView
import com.example.sfcar.mostpopularmovies.viewModels.MostPopularMoviesViewModel
import com.example.sfcar.mostpopularmovies.views.mostPopularMovies.MostPopularMoviesView
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.never
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock


class MostPopularMoviesViewModelTest : UnitTest() {

    private lateinit var viewModel: MostPopularMoviesViewModel
    @Mock
    private lateinit var movieUseCase: GetPopularMoviesUseCase
    @Mock
    private lateinit var searchUseCase : SearchMoviesUseCase
    @Mock
    private lateinit var model: ArrayList<BaseMovieView>

    @Before
    fun setup() {
        viewModel = MostPopularMoviesViewModel(movieUseCase, searchUseCase)
    }

//    @Test
//    fun getPopularMoviesTest() {
//        viewModel.getPopularMovies()
//
//        verify(movieUseCase).execute(any(), any())
//    }

//    @Test
//    fun loadDataTest() {
//        viewModel.loadData()
//
//        assert(viewModel.page == 1)
//        assert(!viewModel.loadEndlessData)
//        verify(movieUseCase).execute(any(), any())
//    }

    @Test
    fun loadEndlessDataTest() {
        viewModel.loadEndlessData()
        assert(viewModel.loadEndlessData)
        verify(movieUseCase).execute(any(), any())
    }

//    @Test
//    fun setIsLastPageTrueTest() {
//        viewModel.setIsLastPage(23, 23)
//        assert(viewModel.isLastPage)
//    }

//    @Test
//    fun setIsLastPageFalseTest() {
//        viewModel.setIsLastPage(10, 12)
//        assert(!viewModel.isLastPage)
//    }

//    @Test
//    fun addFooterFalseTest() {
//        viewModel.isLastPage = true
//
//        viewModel.addFooter()
//
//        verify(model, never()).add(any())
//    }
}