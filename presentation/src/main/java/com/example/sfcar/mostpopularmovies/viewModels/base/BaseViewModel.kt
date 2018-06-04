package com.example.sfcar.mostpopularmovies.viewModels.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    var failure: MutableLiveData<Int> = MutableLiveData()

    protected fun handleFailure(errorCode: Int) {
        this.failure.value = errorCode
    }

    abstract fun start()
}