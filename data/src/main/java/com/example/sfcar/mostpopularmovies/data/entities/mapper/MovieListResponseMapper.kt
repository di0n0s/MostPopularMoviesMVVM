package com.example.sfcar.mostpopularmovies.data.entities.mapper

import com.example.sfcar.mostpopularmovies.data.entities.MovieListResponseDto
import com.example.sfcar.mostpopularmovies.domain.model.Movie

object MovieListResponseMapper {

    fun toBusinessObject(movieListResponseDto: MovieListResponseDto?): List<Movie> {
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