package com.example.sfcar.mostpopularmovies.domain.interactor.usecase

import com.example.sfcar.mostpopularmovies.UnitTest
import com.example.sfcar.mostpopularmovies.domain.executor.PostExecutionThread
import com.example.sfcar.mostpopularmovies.domain.interactor.params.MostPopularMoviesParams
import com.example.sfcar.mostpopularmovies.domain.repository.MovieRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock


class GetPopularMoviesUseCaseTest : UnitTest() {

    private lateinit var useCase: GetPopularMoviesUseCase
    @Mock
    private lateinit var repository: MovieRepository
    @Mock
    private lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        useCase = GetPopularMoviesUseCase(repository, postExecutionThread)
    }

    @Test
    fun buildUseCaseObservableTest() {
        useCase.buildUseCaseObservable(MostPopularMoviesParams(23))

        verify(repository).getPopularMovies(any())
    }
}