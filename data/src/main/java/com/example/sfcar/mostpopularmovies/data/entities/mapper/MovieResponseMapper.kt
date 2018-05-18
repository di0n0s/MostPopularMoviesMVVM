package com.example.sfcar.mostpopularmovies.data.entities.mapper

import com.example.sfcar.mostpopularmovies.data.entities.MovieResponseDto
import com.example.sfcar.mostpopularmovies.domain.model.Movie

object MovieResponseMapper {

    fun toBusinessObject(movieResponseDto: MovieResponseDto): Movie {
        return Movie(movieResponseDto.original_title ?: "",
                movieResponseDto.release_date ?: "",
                movieResponseDto.overview ?: "",
                movieResponseDto.poster_path ?: "")
    }
}