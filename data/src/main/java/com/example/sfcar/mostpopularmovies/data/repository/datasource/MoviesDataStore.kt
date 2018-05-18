package com.example.sfcar.mostpopularmovies.data.repository.datasource

import com.example.sfcar.mostpopularmovies.data.entities.base.MovieListResponseDto
import io.reactivex.Observable

interface MoviesDataStore {

    fun getPopularMovies(page: Int): Observable<MovieListResponseDto>
}