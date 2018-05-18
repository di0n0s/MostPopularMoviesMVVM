package com.example.sfcar.mostpopularmovies.data.repository.datasource

import com.example.sfcar.mostpopularmovies.data.entities.MovieListResponseDto
import io.reactivex.Observable

interface MoviesDataStore {

    fun getPopularMovies(page: Int): Observable<MovieListResponseDto>
}