package com.example.sfcar.mostpopularmovies.model

data class MovieListPaginationView(var pagesNumber: Int = 0,
                                   var currentPage: Int = 0,
                                   var movieList: ArrayList<BaseMovieView> = ArrayList())