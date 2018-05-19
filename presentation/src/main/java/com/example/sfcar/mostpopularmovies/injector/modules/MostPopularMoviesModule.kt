package com.example.sfcar.mostpopularmovies.injector.modules

import com.example.sfcar.mostpopularmovies.injector.PerFragment
import com.example.sfcar.mostpopularmovies.views.mostPopularMovies.MostPopularMoviesFragment
import com.example.sfcar.mostpopularmovies.views.mostPopularMovies.MostPopularMoviesView
import dagger.Module
import dagger.Provides

@Module
class MostPopularMoviesModule(private val mostPopularMoviesFragment: MostPopularMoviesFragment) {

    @Provides
    @PerFragment
    fun provideMostPopularMoviesView(): MostPopularMoviesView {
        return mostPopularMoviesFragment
    }
}