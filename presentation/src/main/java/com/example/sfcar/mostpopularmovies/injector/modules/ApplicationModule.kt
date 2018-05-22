package com.example.sfcar.mostpopularmovies.injector.modules

import android.content.Context
import com.example.sfcar.mostpopularmovies.MostPopularMoviesApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule (private val application : MostPopularMoviesApplication) {

    @Provides
    @Singleton
    fun provideApplication() : Context{
        return application
    }
}