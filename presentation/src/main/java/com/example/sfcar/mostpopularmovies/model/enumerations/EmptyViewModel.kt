package com.example.sfcar.mostpopularmovies.model.enumerations

import com.example.sfcar.mostpopularmovies.R

enum class EmptyViewModel(var imageId: Int, var title: Int, var subtitle: Int) {
    EMPTY_MOST_POPULAR_MOVIES(R.drawable.ic_movie_placeholder, R.string.most_popular_movies_empty_title, R.string.most_popular_movies_empty_subtitle)
}