package com.example.sfcar.mostpopularmovies.data.repository

import com.example.sfcar.mostpopularmovies.data.entities.mapper.MovieListResponseMapper
import com.example.sfcar.mostpopularmovies.data.repository.datasource.MoviesDataStore
import com.example.sfcar.mostpopularmovies.domain.model.Movie
import com.example.sfcar.mostpopularmovies.domain.repository.MovieRepository
import io.reactivex.Observable
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(private val moviesDataStore: MoviesDataStore) : MovieRepository {

    override fun getPopularMovies(page: Int): Observable<List<Movie>> =
            moviesDataStore.getPopularMovies(page).map { MovieListResponseMapper.toBusinessObject(it) }

}