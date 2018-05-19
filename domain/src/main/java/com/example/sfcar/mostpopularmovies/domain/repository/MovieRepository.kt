package com.example.sfcar.mostpopularmovies.domain.repository

import com.example.sfcar.mostpopularmovies.domain.model.MovieListPagination
import io.reactivex.Observable

interface MovieRepository {

    fun getPopularMovies(page: Int): Observable<MovieListPagination>
}