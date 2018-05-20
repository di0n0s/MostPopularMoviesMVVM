package com.example.sfcar.mostpopularmovies.data.model.mapper

import com.example.sfcar.mostpopularmovies.data.model.MovieListResponseDto
import com.example.sfcar.mostpopularmovies.domain.model.Movie
import com.example.sfcar.mostpopularmovies.domain.model.MovieListPagination

object MovieListResponseMapper {

    fun toPaginationViewModel(movieListResponseDto: MovieListResponseDto?): MovieListPagination =
            MovieListPagination(movieListResponseDto?.total_pages ?: 0,
                    movieListResponseDto?.page ?: 0,
                    toBusinessObject(movieListResponseDto))

    private fun toBusinessObject(movieListResponseDto: MovieListResponseDto?): List<Movie> {
        val movieList = ArrayList<Movie>()
        if (movieListResponseDto != null) {
            val results = movieListResponseDto.results
            if (results != null && results.isNotEmpty()) {
                results.forEach {
                    //                    if (it.title != null && it.release_date != null && it.overview != null && it.backdrop_path != null)
                    movieList.add(MovieResponseMapper.toBusinessObject(it))
                }
            }
        }
        return movieList
    }
}