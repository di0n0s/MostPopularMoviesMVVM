package com.example.sfcar.mostpopularmovies.extensions

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer

fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) =
        liveData.observe(this, Observer(body))

fun <L : LiveData<Int>> LifecycleOwner.failure(liveData: L, body: (Int?) -> Unit) =
        liveData.observe(this, Observer(body))