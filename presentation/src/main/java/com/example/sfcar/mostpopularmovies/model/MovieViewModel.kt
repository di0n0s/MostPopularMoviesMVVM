package com.example.sfcar.mostpopularmovies.model

import android.os.Parcel
import android.os.Parcelable

open class MovieViewModel(var title: String = "",
                          var releaseDate: String = "",
                          var overview: String = "",
                          var picturePath: String = "") : BaseMovieViewModel(), Parcelable {
    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(title)
        writeString(releaseDate)
        writeString(overview)
        writeString(picturePath)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MovieViewModel> = object : Parcelable.Creator<MovieViewModel> {
            override fun createFromParcel(source: Parcel): MovieViewModel = MovieViewModel(source)
            override fun newArray(size: Int): Array<MovieViewModel?> = arrayOfNulls(size)
        }
    }
}
