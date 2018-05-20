package com.example.sfcar.mostpopularmovies.model.mapper

import android.content.Context
import com.example.sfcar.mostpopularmovies.R
import com.example.sfcar.mostpopularmovies.data.net.ApiConstants
import com.example.sfcar.mostpopularmovies.domain.model.Movie
import com.example.sfcar.mostpopularmovies.domain.model.MovieListPagination
import com.example.sfcar.mostpopularmovies.model.BaseMovieViewModel
import com.example.sfcar.mostpopularmovies.model.MovieListPaginationViewModel
import com.example.sfcar.mostpopularmovies.model.MovieViewModel

object MovieListPaginationViewModelMapper {

    fun turnInto(movieListPagination: MovieListPagination, context: Context): MovieListPaginationViewModel =
            MovieListPaginationViewModel(movieListPagination.pagesNumber,
                    movieListPagination.currentPage,
                    toListViewModel(movieListPagination.movieList, context))

    private fun toViewModel(bO: Movie, context: Context): MovieViewModel =
            MovieViewModel(bO.title,
                    setYearString(context, bO.releaseDate),
                    bO.overview,
                    setPictureUrl(bO.picturePath))

    private fun releaseDateToYear(releaseDate: String): CharSequence =
            releaseDate.subSequence(0, 4)

    private fun setYearString(context: Context, releaseDate: String): String {
        return if (releaseDate.isNotBlank())
            releaseDateToYear(releaseDate).toString()
        else
            context.getString(R.string.unknown_date)
    }

    private fun setPictureUrl(picturePath: String): String =
            "${ApiConstants.BASE_URL_IMAGE}${ApiConstants.IMAGE_SIZE_W342}$picturePath"

    private fun toListViewModel(bOL: Collection<Movie>?, context: Context): ArrayList<BaseMovieViewModel> {
        val modelList = ArrayList<BaseMovieViewModel>()
        if (bOL != null && bOL.isNotEmpty()) {
            bOL.forEach {
                modelList.add(toViewModel(it, context))
            }
        }
        return modelList
    }


}