package com.example.sfcar.mostpopularmovies.injector.components

import com.example.sfcar.mostpopularmovies.injector.PerFragment
import com.example.sfcar.mostpopularmovies.injector.modules.BaseFragmentModule
import com.example.sfcar.mostpopularmovies.injector.modules.MovieDetailModule
import com.example.sfcar.mostpopularmovies.views.movieDetail.MovieDetailFragment
import dagger.Subcomponent

@PerFragment
@Subcomponent(modules = [BaseFragmentModule::class, MovieDetailModule::class])
interface MovieDetailComponent {

    fun inject(movieDetailFragment: MovieDetailFragment)
}