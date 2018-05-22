package com.example.sfcar.mostpopularmovies.data.net

import com.example.sfcar.mostpopularmovies.data.model.MovieListResponseDto
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page: Int): Observable<MovieListResponseDto>

    @GET("search/movie")
    fun searchMovies(@Query("page") page: Int,
                     @Query("query") query: String,
                     @Query("include_adult") viewPornography: Boolean): Observable<MovieListResponseDto>
}