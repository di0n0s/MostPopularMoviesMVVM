package com.example.sfcar.mostpopularmovies.data.repository.datasource

import com.example.sfcar.mostpopularmovies.data.entities.base.MovieListResponseDto
import io.reactivex.Observable

interface MoviesDataSource {

    fun getPopularMovies(page: Int): Observable<MovieListResponseDto>
}