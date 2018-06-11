package com.example.sfcar.mostpopularmovies.domain.model

data class Movie(var id: Int = 0,
                 var title: String = "",
                 var releaseDate: String = "",
                 var overview: String = "",
                 var picturePath: String = "")