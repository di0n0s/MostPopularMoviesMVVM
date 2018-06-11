package com.example.sfcar.mostpopularmovies.model

import android.os.Parcel
import android.os.Parcelable

open class MovieView(var id: Int = 0,
                     var picturePath: String = "") : BaseMovieView(), Parcelable {
    constructor(source: Parcel) : this(
            source.readInt(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(id)
        writeString(picturePath)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<MovieView> = object : Parcelable.Creator<MovieView> {
            override fun createFromParcel(source: Parcel): MovieView = MovieView(source)
            override fun newArray(size: Int): Array<MovieView?> = arrayOfNulls(size)
        }
    }
}