package com.example.sfcar.mostpopularmovies.injector.components

import com.example.sfcar.mostpopularmovies.injector.modules.*
import com.example.sfcar.mostpopularmovies.views.base.BaseActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, MoviesApiModule::class])
interface ApplicationComponent {

    fun inject(baseActivity: BaseActivity)

    fun plus(baseFragmentModule: BaseFragmentModule, baseListModule: BaseListModule, mostPopularMoviesModule: MostPopularMoviesModule): MostPopularMoviesComponent



}