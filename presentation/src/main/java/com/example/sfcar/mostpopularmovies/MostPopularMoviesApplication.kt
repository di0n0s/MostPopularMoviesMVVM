package com.example.sfcar.mostpopularmovies

import android.app.Application
import com.example.sfcar.mostpopularmovies.injector.components.ApplicationComponent
import com.example.sfcar.mostpopularmovies.injector.components.DaggerApplicationComponent
import com.example.sfcar.mostpopularmovies.injector.modules.ApplicationModule

class MostPopularMoviesApplication : Application() {

    companion object {
        lateinit var instance : MostPopularMoviesApplication
        @JvmStatic
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initializeInjector()
    }

    private fun initializeInjector() {
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build()
    }
}