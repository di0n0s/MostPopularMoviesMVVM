package com.example.sfcar.mostpopularmovies.injector.modules

import android.content.Context
import com.example.sfcar.mostpopularmovies.R
import com.example.sfcar.mostpopularmovies.injector.PerFragment
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class MovieDetailModule {

    @Provides
    @PerFragment
    @Named("unknownDate")
    fun provideUnknownDateString(context: Context): String = context.getString(R.string.unknown_date)
}