package com.example.sfcar.mostpopularmovies.observers.base

import com.example.sfcar.mostpopularmovies.model.enumerations.ErrorEnum
import com.example.sfcar.mostpopularmovies.presenters.base.BaseApiPresenter
import io.reactivex.observers.DisposableObserver
import retrofit2.HttpException
import java.net.UnknownHostException

abstract class BaseObserver<T>(private val presenter: BaseApiPresenter) : DisposableObserver<T>() {

    override fun onComplete() {
    }

    override fun onError(e: Throwable) {
        when (e) {
            is HttpException -> presenter.onErrorReceived(e.response().code())
            is UnknownHostException -> presenter.onErrorReceived(ErrorEnum.NINETY_NINE.httpStatus)
            else -> presenter.onErrorReceived(ErrorEnum.NINETY_NINE.httpStatus)
        }
    }
}