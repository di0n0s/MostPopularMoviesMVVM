package com.example.sfcar.mostpopularmovies.observers

import android.content.Context
import com.example.sfcar.mostpopularmovies.domain.model.MovieListPagination
import com.example.sfcar.mostpopularmovies.model.enumerations.ErrorEnum
import com.example.sfcar.mostpopularmovies.presenters.mostPopularMovies.MostPopularMoviesPresenterImp
import io.reactivex.observers.DisposableObserver
import retrofit2.HttpException
import java.net.UnknownHostException

class MostPopularMoviesObserver(private val presenter: MostPopularMoviesPresenterImp, private val context: Context) : DisposableObserver<MovieListPagination>() {

    override fun onComplete() {
    }

    override fun onNext(t: MovieListPagination) {
        presenter.onMovieListReceived(t)
    }

    override fun onError(e: Throwable) {
        when (e) {
            is HttpException -> presenter.onErrorReceived(ErrorEnum.findErrorDescriptionByErrorCode(e.response().code(), context))
            is UnknownHostException -> presenter.onErrorReceived(ErrorEnum.findErrorDescriptionByErrorCode(ErrorEnum.NINETY_NINE.httpStatus, context))
            else -> presenter.onErrorReceived(ErrorEnum.findErrorDescriptionByErrorCode(ErrorEnum.NINETY_NINE.httpStatus, context))
        }
    }

}