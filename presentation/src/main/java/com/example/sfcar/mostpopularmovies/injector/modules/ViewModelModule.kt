package com.example.sfcar.mostpopularmovies.injector.modules

import android.arch.lifecycle.ViewModelProvider
import com.example.sfcar.mostpopularmovies.viewModels.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}