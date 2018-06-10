package com.example.sfcar.mostpopularmovies.model.mapper

import com.example.sfcar.mostpopularmovies.data.net.ApiConstants
import javax.inject.Inject
import javax.inject.Named

class MovieListPaginationViewModelMapper {

    @Inject
    @Named("unknownDate")
    lateinit var unknownDateString: String

//    fun turnInto(movieListPagination: MovieListPagination, context: Context): MovieListPaginationViewModel =
//            MovieListPaginationViewModel(movieListPagination.pagesNumber,
//                    movieListPagination.currentPage,
//                    toListViewModel(movieListPagination.movieList, context))

//    private fun toViewModel(bO: Movie, context: Context): MovieViewModel =
//            MovieViewModel(bO.title,
//                    setYearString(context, bO.releaseDate),
//                    bO.overview,
//                    setPictureUrl(bO.picturePath))

    private fun releaseDateToYear(releaseDate: String): CharSequence =
            releaseDate.subSequence(0, 4)

    fun setYearString(releaseDate: String): String {
        return if (releaseDate.isNotBlank())
            releaseDateToYear(releaseDate).toString()
        else
            unknownDateString
    }

    fun setPictureUrl(picturePath: String): String =
            "${ApiConstants.BASE_URL_IMAGE}${ApiConstants.IMAGE_SIZE_W342}$picturePath"

//    private fun toListViewModel(bOL: Collection<Movie>?, context: Context): ArrayList<BaseMovieViewModel> {
//        val modelList = ArrayList<BaseMovieViewModel>()
//        if (bOL != null && bOL.isNotEmpty()) {
//            bOL.forEach {
//                modelList.add(toViewModel(it, context))
//            }
//        }
//        return modelList
//    }


}