package com.example.sfcar.mostpopularmovies.injector.modules

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.sfcar.mostpopularmovies.viewModels.MostPopularMoviesViewModel
import com.example.sfcar.mostpopularmovies.viewModels.base.ViewModelFactory
import com.example.sfcar.mostpopularmovies.viewModels.base.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MostPopularMoviesViewModel::class)
    abstract fun bindsMoviesViewModel(moviesViewModel: MostPopularMoviesViewModel): ViewModel

//    @Binds
//    @IntoMap
//    @ViewModelKey(MovieDetailsViewModel::class)
//    abstract fun bindsMovieDetailsViewModel(movieDetailsViewModel: MovieDetailsViewModel): ViewModel
}