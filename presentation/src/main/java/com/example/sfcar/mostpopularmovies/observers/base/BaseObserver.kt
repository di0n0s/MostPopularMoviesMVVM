package com.example.sfcar.mostpopularmovies.observers.base

import com.example.sfcar.mostpopularmovies.model.enumerations.ErrorEnum
import com.example.sfcar.mostpopularmovies.viewModels.base.BaseViewModel
import io.reactivex.observers.DisposableObserver
import retrofit2.HttpException
import java.net.UnknownHostException

abstract class BaseObserver<T>(private val viewModel: BaseViewModel) : DisposableObserver<T>() {

    override fun onComplete() {
    }

    override fun onError(e: Throwable) {
        when (e) {
            is HttpException -> viewModel.handleError(e.response().code())
            is UnknownHostException -> viewModel.handleError(ErrorEnum.NINETY_NINE.httpStatus)
            else -> viewModel.handleError(ErrorEnum.NINETY_NINE.httpStatus)
        }
    }
}