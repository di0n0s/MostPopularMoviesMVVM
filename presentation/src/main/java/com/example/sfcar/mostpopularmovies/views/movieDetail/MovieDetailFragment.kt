package com.example.sfcar.mostpopularmovies.views.movieDetail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.sfcar.mostpopularmovies.R
import com.example.sfcar.mostpopularmovies.model.MovieViewModel
import com.example.sfcar.mostpopularmovies.views.base.BaseFragment


/**
 * A simple [Fragment] subclass.
 *
 */
class MovieDetailFragment : BaseFragment() {

    companion object {
        const val TAG = "MovieDetailFragment"
        private const val ARG_MOVIE = "ArgMovie"
        fun newInstance(movieViewModel: MovieViewModel): MovieDetailFragment {
            val args = Bundle()
            val fragment = MovieDetailFragment()
            args.putParcelable(ARG_MOVIE, movieViewModel)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun setupFragmentComponent() {
    }

    private fun setTextViews(){

    }

}
