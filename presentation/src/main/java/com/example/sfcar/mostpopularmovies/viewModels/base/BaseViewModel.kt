package com.example.sfcar.mostpopularmovies.viewModels.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    var failure: MutableLiveData<Int> = MutableLiveData()

    fun handleError(errorCode: Int) {
        this.failure.value = errorCode
    }

    abstract fun start()
}