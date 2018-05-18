package com.example.sfcar.mostpopularmovies.injector.components

import com.example.sfcar.mostpopularmovies.injector.modules.ApplicationModule
import com.example.sfcar.mostpopularmovies.views.base.BaseActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(baseActivity: BaseActivity)
}