package com.example.sfcar.mostpopularmovies.data.repository

import com.example.sfcar.mostpopularmovies.data.model.mapper.MovieListResponseMapper
import com.example.sfcar.mostpopularmovies.data.repository.datasource.MoviesDataStore
import com.example.sfcar.mostpopularmovies.domain.model.MovieListPagination
import com.example.sfcar.mostpopularmovies.domain.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(private val moviesDataStore: MoviesDataStore) : MovieRepository {

    override fun getPopularMovies(page: Int): Observable<MovieListPagination> =
            moviesDataStore.getPopularMovies(page).map { MovieListResponseMapper.toPaginationViewModel(it) }

}