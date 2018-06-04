package com.example.sfcar.mostpopularmovies.views.base


import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 *
 */
abstract class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupFragmentComponent()
    }

    protected fun showToastMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    protected abstract fun setupFragmentComponent()


}
