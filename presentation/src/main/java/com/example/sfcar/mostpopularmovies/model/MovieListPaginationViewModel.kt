package com.example.sfcar.mostpopularmovies.model

data class MovieListPaginationViewModel(var pagesNumber: Int = 0,
                                        var currentPage: Int = 1,
                                        var movieList: ArrayList<MovieViewModel> = ArrayList())