package com.example.sfcar.mostpopularmovies.model

import android.os.Parcel
import android.os.Parcelable

open class MovieDetailView(id: Int = 0,
                           picturePath: String = "",
                           var title: String = "",
                           var releaseDate: String = "",
                           var overview: String = "") : MovieView(id, picturePath), Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(picturePath)
        writeString(title)
        writeString(releaseDate)
        writeString(overview)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MovieDetailView> = object : Parcelable.Creator<MovieDetailView> {
            override fun createFromParcel(source: Parcel): MovieDetailView = MovieDetailView(source)
            override fun newArray(size: Int): Array<MovieDetailView?> = arrayOfNulls(size)
        }
    }
}