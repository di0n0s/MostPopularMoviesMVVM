package com.example.sfcar.mostpopularmovies.views.movieDetail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sfcar.mostpopularmovies.MostPopularMoviesApplication
import com.example.sfcar.mostpopularmovies.R
import com.example.sfcar.mostpopularmovies.injector.modules.BaseFragmentModule
import com.example.sfcar.mostpopularmovies.injector.modules.MovieDetailModule
import com.example.sfcar.mostpopularmovies.model.MovieViewModel
import com.example.sfcar.mostpopularmovies.presenters.movieDetail.MovieDetailPresenterImp
import com.example.sfcar.mostpopularmovies.views.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 *
 */
class MovieDetailFragment : BaseFragment() {

    @Inject
    lateinit var presenter: MovieDetailPresenterImp

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.setModel(arguments?.getParcelable(ARG_MOVIE)!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTextViews()

    }

    override fun setupFragmentComponent() {
        MostPopularMoviesApplication
                .applicationComponent
                .plus(BaseFragmentModule(this.context!!), MovieDetailModule())
                .inject(this)
    }

    private fun setTextViews() {
        movieDateTextView.text = presenter.movieViewModel.releaseDate
        movieOverviewTextView.text = presenter.movieViewModel.overview
    }

}
