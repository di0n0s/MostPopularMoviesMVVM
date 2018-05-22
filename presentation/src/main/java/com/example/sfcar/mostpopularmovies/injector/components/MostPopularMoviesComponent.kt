package com.example.sfcar.mostpopularmovies.injector.components

import com.example.sfcar.mostpopularmovies.injector.PerFragment
import com.example.sfcar.mostpopularmovies.injector.modules.BaseFragmentModule
import com.example.sfcar.mostpopularmovies.injector.modules.BaseListModule
import com.example.sfcar.mostpopularmovies.injector.modules.MostPopularMoviesModule
import com.example.sfcar.mostpopularmovies.views.mostPopularMovies.MostPopularMoviesFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [BaseFragmentModule::class, BaseListModule::class, MostPopularMoviesModule::class])
interface MostPopularMoviesComponent {

    fun inject(mostPopularMoviesFragment: MostPopularMoviesFragment)
}