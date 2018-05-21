package com.example.sfcar.mostpopularmovies.domain.interactor.params

data class SearchMoviesParams(val page: Int, val query: String, val loadEndlessData: Boolean) : Params(page)