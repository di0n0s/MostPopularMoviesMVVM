package com.example.sfcar.mostpopularmovies.injector.modules

import android.support.v7.widget.StaggeredGridLayoutManager
import com.example.sfcar.mostpopularmovies.injector.PerFragment
import dagger.Module
import dagger.Provides

@Module
class BaseListModule {

    @Provides
    @PerFragment
    fun provideStaggeredGridLayoutManager(): StaggeredGridLayoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

}