package com.example.sfcar.mostpopularmovies.model.mapper

import com.example.sfcar.mostpopularmovies.data.net.ApiConstants
import javax.inject.Inject
import javax.inject.Named

class MovieListPaginationViewModelMapper {

    @Inject
    @Named("unknownDate")
    lateinit var unknownDateString: String

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
}