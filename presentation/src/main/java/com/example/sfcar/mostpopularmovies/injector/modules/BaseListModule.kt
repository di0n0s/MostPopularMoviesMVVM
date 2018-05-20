package com.example.sfcar.mostpopularmovies.injector.modules

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.example.sfcar.mostpopularmovies.injector.PerFragment
import dagger.Module
import dagger.Provides

@Module
class BaseListModule(private val context: Context) {

    @Provides
    @PerFragment
    fun provideGridLayoutManager(): GridLayoutManager = GridLayoutManager(context, 3)

    @Provides
    @PerFragment
    fun provideLinearLayoutManager(context: Context): LinearLayoutManager {
        return LinearLayoutManager(context)
    }

}