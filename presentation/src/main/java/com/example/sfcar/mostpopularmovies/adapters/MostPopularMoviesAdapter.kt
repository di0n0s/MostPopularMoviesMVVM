package com.example.sfcar.mostpopularmovies.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sfcar.mostpopularmovies.R
import com.example.sfcar.mostpopularmovies.interfaces.AdapterListOnClickListener
import com.example.sfcar.mostpopularmovies.model.BaseMovieViewModel
import com.example.sfcar.mostpopularmovies.model.MovieViewModel
import com.example.sfcar.mostpopularmovies.views.mostPopularMovies.MostPopularMoviesFooterHolder
import com.example.sfcar.mostpopularmovies.views.mostPopularMovies.MostPopularMoviesHolder

class MostPopularMoviesAdapter(private val movieList: List<BaseMovieViewModel>, private val viewListener: AdapterListOnClickListener.ViewListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), AdapterListOnClickListener.AdapterListener {

    companion object {
        const val MOVIE_TYPE = 1
        const val FOOTER_TYPE = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            when (viewType) {
                MOVIE_TYPE ->
                    MostPopularMoviesHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_movie, parent, false), this)
                else -> {
                    MostPopularMoviesFooterHolder(LayoutInflater.from(parent.context).inflate(R.layout.generic_footer, parent, false))
                }

            }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MostPopularMoviesHolder -> holder.bindMovie(movieList[position] as MovieViewModel)
            is MostPopularMoviesFooterHolder -> holder.bindProgressBar()
        }
    }

    override fun getItemViewType(position: Int): Int =
            when (movieList[position]) {
                is MovieViewModel -> MOVIE_TYPE
                else -> FOOTER_TYPE
            }

    override fun onItemSelected(position: Int, view: View) {
        viewListener.onItemSelected(position, view)
    }
}