package com.example.sfcar.mostpopularmovies.data.repository.datasource

import com.example.sfcar.UnitTest
import com.example.sfcar.mostpopularmovies.data.net.MoviesApiService
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class MoviesDataStoreImplTest : UnitTest() {

    private lateinit var dataStore: MoviesDataStoreImpl
    @Mock
    private lateinit var apiService: MoviesApiService

    @Before
    fun setup() {
        dataStore = MoviesDataStoreImpl(apiService)
    }

    @Test
    fun getPopularMoviesTest() {
        dataStore.getPopularMovies(756)

        verify(apiService).getPopularMovies(any())
    }
}