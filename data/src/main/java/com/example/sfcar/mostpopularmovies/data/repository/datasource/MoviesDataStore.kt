package com.example.sfcar.mostpopularmovies.data.repository.datasource

import com.example.sfcar.mostpopularmovies.data.model.MovieListResponseDto
import io.reactivex.Observable

interface MoviesDataStore {

    fun getPopularMovies(page: Int): Observable<MovieListResponseDto>
}