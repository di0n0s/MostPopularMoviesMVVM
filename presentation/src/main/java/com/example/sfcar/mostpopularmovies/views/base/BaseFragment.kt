package com.example.sfcar.mostpopularmovies.views.base


import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.Toast


/**
 * A simple [Fragment] subclass.
 *
 */
abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupFragmentComponent()
    }

    protected fun showToastMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    protected abstract fun setupFragmentComponent()


}
