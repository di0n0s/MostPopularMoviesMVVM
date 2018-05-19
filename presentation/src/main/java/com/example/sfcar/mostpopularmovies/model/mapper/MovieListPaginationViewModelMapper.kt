package com.example.sfcar.mostpopularmovies.model.mapper

import com.example.sfcar.mostpopularmovies.domain.model.Movie
import com.example.sfcar.mostpopularmovies.domain.model.MovieListPagination
import com.example.sfcar.mostpopularmovies.model.MovieListPaginationViewModel
import com.example.sfcar.mostpopularmovies.model.MovieViewModel

object MovieListPaginationViewModelMapper {

    fun turnInto(movieListPagination: MovieListPagination): MovieListPaginationViewModel =
            MovieListPaginationViewModel(movieListPagination.pagesNumber,
                    movieListPagination.currentPage,
                    toListViewModel(movieListPagination.movieList))

    private fun toViewModel(bO: Movie): MovieViewModel =
            MovieViewModel(bO.title, bO.releaseDate, bO.overview, bO.picturePath)

    private fun toListViewModel(bOL: Collection<Movie>?): ArrayList<MovieViewModel> {
        val modelList = ArrayList<MovieViewModel>()
        if (bOL != null && bOL.isNotEmpty()) {
            bOL.forEach {
                modelList.add(toViewModel(it))
            }
        }
        return modelList
    }


}