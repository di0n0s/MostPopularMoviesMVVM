package com.example.sfcar.mostpopularmovies.views.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.sfcar.mostpopularmovies.MostPopularMoviesApplication
import com.example.sfcar.mostpopularmovies.R
import com.example.sfcar.mostpopularmovies.navigation.Navigator
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    protected lateinit var navigator: Navigator
    protected var currentTag: String? = null
    protected var currentFragment: Fragment? = null

    companion object {
        private const val CURRENT_FRAGMENT_TAG: String = "CURRENT_FRAGMENT_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeFragmentAndTAG(savedInstanceState)
        setContentView(R.layout.activity_base)
        setupActivityComponent()
        beginTransaction()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        supportFragmentManager.putFragment(outState, currentTag, currentFragment)
        outState?.putString(CURRENT_FRAGMENT_TAG, currentTag)
        super.onSaveInstanceState(outState)
    }

    private fun initializeFragmentAndTAG(savedInstanceState: Bundle?) {
        if (savedInstanceState == null || !savedInstanceState.containsKey(CURRENT_FRAGMENT_TAG)) {
            createFragmentAndSettingTAG()
        } else {
            currentTag = savedInstanceState.getString(CURRENT_FRAGMENT_TAG)
            currentFragment = supportFragmentManager.getFragment(savedInstanceState, currentTag)
        }
    }

    private fun beginTransaction() =
            supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, currentFragment, currentTag).commit()

    private fun setupActivityComponent() {
        MostPopularMoviesApplication
                .applicationComponent
                .inject(this)
    }

    abstract fun setToolbarTitle()

    abstract fun createFragmentAndSettingTAG()

}
