package com.example.sfcar.mostpopularmovies.domain.model

data class MovieListPagination(var pagesNumber: Int = 0,
                               var currentPage: Int = 1,
                               var movieList: List<Movie> = ArrayList())