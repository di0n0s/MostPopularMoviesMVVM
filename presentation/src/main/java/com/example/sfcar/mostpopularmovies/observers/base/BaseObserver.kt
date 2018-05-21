package com.example.sfcar.mostpopularmovies.observers.base

import android.content.Context
import com.example.sfcar.mostpopularmovies.model.enumerations.ErrorEnum
import com.example.sfcar.mostpopularmovies.presenters.base.BaseApiPresenter
import io.reactivex.observers.DisposableObserver
import retrofit2.HttpException
import java.net.UnknownHostException

abstract class BaseObserver<T>(private val presenter: BaseApiPresenter,
                               private val context: Context) : DisposableObserver<T>() {

    override fun onComplete() {
    }

    override fun onError(e: Throwable) {
        when (e) {
            is HttpException -> presenter.onErrorReceived(ErrorEnum.findErrorDescriptionByErrorCode(e.response().code(), context))
            is UnknownHostException -> presenter.onErrorReceived(ErrorEnum.findErrorDescriptionByErrorCode(ErrorEnum.NINETY_NINE.httpStatus, context))
            else -> presenter.onErrorReceived(ErrorEnum.findErrorDescriptionByErrorCode(ErrorEnum.NINETY_NINE.httpStatus, context))
        }
    }
}